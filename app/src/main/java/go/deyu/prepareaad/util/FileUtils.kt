package go.deyu.prepareaad.util

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import java.io.*

object FileUtils {

    private val BackupRelativeDirPath =
        Environment.DIRECTORY_DOCUMENTS + "/MyFile/"
    private val BackupDir = "Backup"
    private val hasQ = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    @JvmStatic
    fun isExternalStorageAvailable(): Boolean =
        try {
            Environment.getExternalStorageState()
        } catch (e: NullPointerException) { // (sh)it happens (Issue #660)
            ""
        } catch (e: IncompatibleClassChangeError) { // (sh)it happens too (Issue #989)
            ""
        } == Environment.MEDIA_MOUNTED

    @JvmStatic
    fun canAccessExternalStorage(context: Context): Boolean =
        (hasQ || UtilsPermissions.hasPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                && isExternalStorageAvailable()


    @JvmStatic
    fun getBackupFileOutStream(context: Context, fileName: String): OutputStream {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return writeInMediaStoreOutputStream(context = context,fileName = fileName)
        } else {
            val backupDir =
                getExternalFilesDirectory(context, null, fileName)
            val backupUserTagFile = File(backupDir, fileName)
            backupUserTagFile.createNewFile()
            return FileOutputStream(backupUserTagFile)
        }
    }


    @JvmStatic
    fun getBackupFileInputStream(context: Context, fileName: String): InputStream {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return readInMediaStoreInputStream(context = context,fileName = fileName)
        } else {
            val backupDir =
                getExternalFilesDirectory(context, null, BackupDir)
            val backupUserTagFile = File(backupDir, fileName)
            return FileInputStream(backupUserTagFile)
        }
    }

    @JvmStatic
    fun isBackupFileExist(context: Context, fileName: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return getFileId(context = context,fileName = fileName) > -1L
        } else {
            val backupDir = getExternalFilesDirectory(context, null, BackupDir)
            if (backupDir != null && !backupDir.exists()) {
                return false
            }
            val backupFile = File(backupDir, fileName)
            return backupFile.exists()
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun writeInMediaStoreOutputStream(context: Context, fileName: String): OutputStream {
        val fileId = getFileId(context, fileName)
        if (fileId == -1L) {
            return newFileOutputStream(context = context,fileName = fileName)
        } else {
            return overwriteFileOutPutStream(context = context,id = fileId)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun readInMediaStoreInputStream(context: Context, fileName: String): InputStream {
        val fileId = getFileId(context, fileName)
        val contentUri = MediaStore.Files.getContentUri("external")
        val uri = ContentUris.withAppendedId(contentUri, fileId);
        return  context.getContentResolver().openInputStream(uri)!!
    }

    private fun newFileOutputStream(context: Context, fileName: String): OutputStream {
        ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);       //file name
             //file extension, will automatically add to file
            put(MediaStore.MediaColumns.RELATIVE_PATH, BackupRelativeDirPath);
            val uri =
                context.contentResolver.insert(MediaStore.Files.getContentUri("external"), this)
            return context.contentResolver.openOutputStream(uri!!)!!
        }
    }

    private fun overwriteFileOutPutStream(context: Context ,id : Long): OutputStream {
        val contentUri = MediaStore.Files.getContentUri("external")
        val uri = ContentUris.withAppendedId(contentUri, id);
        return context.getContentResolver().openOutputStream(uri, "rwt")!! //overwrite mode, see below
    }




    private fun getFileId(context: Context, fileName: String): Long {
        val contentUri = MediaStore.Files.getContentUri("external")
        val selection = MediaStore.MediaColumns.RELATIVE_PATH + "=?"
        val selectionArgs = arrayOf(BackupRelativeDirPath)
        val cursor: Cursor =
            context.getContentResolver().query(contentUri, null, selection, selectionArgs, null)!!
        if (cursor.count == 0) {
            cursor.close()
            return -1L
        }
        while (cursor.moveToNext()) {
            val name =
                cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME))
            if (name == fileName) {
                val id = cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns._ID))
                cursor.close()
                return id
            }
        }
        cursor.close()
        return -1L
    }

    @Throws(IllegalStateException::class)
    private fun getExternalFilesDirectory(context: Context?, type: String? = null, sub: String? = null): File? = when {
        context == null -> null
        !isExternalStorageAvailable() -> null
        hasQ  && UtilsPermissions.hasPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)-> context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        UtilsPermissions.hasPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) -> File(Environment.getExternalStorageDirectory(), sub ?: "")
        else -> throw IllegalStateException("No write external permission")
    }
}