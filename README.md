# PrepareAAD
我用來準備AAD認證用的，我打算用Kotlin 。

這證照官方有提供考試內容，主要就五個主題：

1. Android core
2. User interface
3. Data management
4. Debugging
5. Testing


# Android Core 
### 這邊主要要認識的是一些基本屬性。
#### 基本元件：
#### Activity
基本跟User互動的單位，所有的一切基本上都從這裡開始。
#### Service
現在主要是使用在一些前景的服務，最標準的就是音樂播放的服務，需要帶常態顯示的一個Notification讓使用者意識到服務還在用。
以前會用來處理一些背景的工作，不過現在比較推薦使用WorkManager去處理背景工作。
(背景工作，就是生命週期不需要跟App一致的一些工作，譬如：下載檔案，更新，同步資料，傳送Event到Server)
#### Broadcast Recevier
廣播接收者，基本上用來接受以及傳遞事件，可以跨應用程式，一些常見廣播事件，譬如。
分成兩種註冊模式：
動態註冊： 在程式裡面去註冊監聽，也可以在程式之中解除註冊。(有些事件必須要在動態註冊才能使用)
靜態註冊：直接宣告在Manifest上面，就算App沒有被打開，也可以監聽相關事件，常見的像是開機事件。
#### Content providers
基本上所有App裡面的資料是不對外公開的，如果想取得其他App的資料，或者讓其他App取得你的資料，可以使用Content Provides去實現，透過URI 去對應提供相對內容的Providers。
#### Manifest 
跟Android聲明你的App有哪些元件，會使用哪些權限以及設備的一份像是註冊表的東西。

## 一些名詞簡單解釋
### AndroidX
AndroidX 是一系列方便 Android 開發的Libs，Jetpack 系列也是AndroidX，compile version 需要28以上，不過現在Google Play 要求大家都要30以上，基本上一定可以用拉，它的前身為Android support libs(AndroidX之後之前的Android support 庫將不再維護。)
### KTX
KTX 是指使用kotlin語言特性，在原生的一些Class加上一些方便開發以及常用的extension，所以你在import libs的時候要指定ktx會有比較多方便的功能可以用，追求簡短程式碼必備。
譬如 原本：
```
sharedPreferences
        .edit()  // create an Editor
        .putBoolean("key", value)
        .apply() // write to disk asynchronously
```
使用KTX後可以變成
```
sharedPreferences.edit { putBoolean("key", value) }
```
### Localize
就是一些多國語系部分。

### ConstraintLayout
看了證照閱讀內容傳統佈局好像比較多ConstraintLayout的內容(我比較習慣用Compose説 雞掰)，不熟的人可能要稍微看一下，連結佈局之類的，不過這個我以前常用，目前準備就先不贅述了，有空再回來補。

### WorkManager (重點)
主要分成三個部分。
1. Worker ： 類似Runnable 主要執行的程式碼會放在這邊，可以帶inputData 類似Bundle那樣Map的形式(inputData 不能太大)。
2. WorkRequest ： 定義你的Worker應該怎麼樣被執行，要一次還是定時處理，或是要在什麼條件下才執行。
3. WorkManager ： 管理所有WorkerRequest跟排成的地方，也可從這邊取得一個可觀察的WorkInfo來得知目前Work的狀態。

一些特性：
1. 定時重複的PeriodicWorkRequestBuilder 最低的間隔不能低於15 mins，所以不適合做一些太短時間的工作。
2. 可以把工作串連起來， A -> B -> C ，並且將Data一路傳下去。
3. 可以定義唯一工作Tag在Request的時候可以決定要 Replace Cancel Apply 之前的同Tag的工作。
4. Worker能拿來當InputData的資料量有限，只能帶一些簡單的資料，直接塞圖片是沒辦法的，跟Intent有點像。
5. CoroutineWorker 比較好用，可以直接執行suspend fun。

簡單簡述一下流程：
1. 定義你的Worker 就是你要做的事，譬如塞入大量資料，上傳東西，儲存檔案之類的，要input什麼資料。
2. 決定你要什麼樣的處理方式： OneTime ? Periodic ? 需要其他Worker串連嗎？什麼條件下要才要觸發，然後把你的WorkRequest enquene 到WorkManager讓他排程。
3. 需要得知Work狀態的地方，使用WorkManager getWorkInfo By Id Tag之類的去找你的Info ，使用LiveData方式去監聽目前WorkInfo的狀態。(LiveData可以根據LifeCycle 去聽事件如果在ViewModel 使用observeForever 要注意在 OnCleared 時要解除observe)
4. 監聽到 WorkInfo 是 Finish or Fail 的時候可以讀取WorkInfo的OutData去做相對應的事。

### Notification
就是常常吵你的那個東西。

幾個特別的名詞：

MessagingStyle：Android7.0以上開始提供一些官方的通知樣板，讓開發者去做使用，類似聊天類型，圖片類型通知之類的。

Channel : Android8.0以上必須先建立Channel才能打通知，使用者可以根據Channel來開啟或者關閉通知，所以分門別類通知，讓使用者有控制通知的能力。

RemoteInput：用來建立輸入型態的通知，類似聊天軟體那種。

BigTextStyle, which can display a large block of text, such as showing the contents of an email when expanded.
BigPictureStyle, which shows large-format notifications that include a large image attachment.
InboxStyle, which shows a conversation style text content.
MediaStyle, which shows controls for media playback.
MessagingStyle, which shows large-format notifications that include multiple messages between any number of people.


### DataStore - SharePerference 取代方案 // 我猜目前還不是考題
DataStore的優勢是，
1. 可以確保IO時不是在 主線程。
2. 可以把Value用 Flow或者是asLiveData的方式去監聽變化，讓你的UI跟你的SP 是一致的。


使用 inline，行內函數到呼叫的地方，能減少函式呼叫造成的額外開銷，在迴圈中尤其有效
使用 inline 能避免函式的 lambda 形參額外建立 Function 物件
使用 noinline 可以拒絕形參 lambda 內聯
使用 crossinline 顯示宣告 inline 函式的形參 lambda 不能有 return 語句，避免lambda 中的 return 影響外部程式流

### Testing

幾個關鍵名詞
Large Test - 10%
Medium Test - 20%
Small Test - 70%

Robolectric 是一個為 Android 帶來快速可靠的單元測試的框架。 測試在幾秒鐘內在您的工作站上的 JVM 內運行。 使用 Robolectric，您可以編寫如下測試：

```
@RunWith(RobolectricTestRunner.class)
public class MyActivityTest {

  @Test
  public void clickingButton_shouldChangeMessage() {
    MyActivity activity = Robolectric.setupActivity(MyActivity.class);

    activity.button.performClick();

    assertThat(activity.message.getText()).isEqualTo("Robolectric Rocks!");
  }
}
```

Espresso：方便你模擬UI的一些動作做測試，也可以使用Record的方式把操作記錄下來當做測試。

hamcrest：方便做判斷測試的一些function 。 ex: closeTo (這個已經在Androidx那包裡面了)

## 測試Double使用的專有名詞
Fake ：
A test double that has a "working" implementation of the class, but it's implemented in a way that makes it good for tests but unsuitable for production.

Mock：
A test double that tracks which of its methods were called. It then passes or fails a test depending on whether it's methods were called correctly.

Stub：
A test double that includes no logic and only returns what you program it to return. A StubTaskRepository could be programmed to return certain combinations of tasks from getTasks for example.

Dummy：
A test double that is passed around but not used, such as if you just need to provide it as a parameter. If you had a NoOpTaskRepository, it would just implement the TaskRepository with no code in any of the methods.

Spy
A test double which also keeps tracks of some additional information; for example, if you made a SpyTaskRepository, it might keep track of the number of times the addTask method was called.

