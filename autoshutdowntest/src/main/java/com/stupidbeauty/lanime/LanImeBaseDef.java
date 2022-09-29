package com.stupidbeauty.lanime;

import java.io.File;
import android.os.Environment;

/**
 * 一些基本信息的定义。
 * @author root
 *
 */
public class LanImeBaseDef 
{
	// define parms
	public static final int SIMO_MAX_VIEW_CONTROLLER = 20;

	public static final String SIMO_FONT_FANMILY_NAME = "Arial";

	// for xml
	public static final String LOCAL_STRING_CN_FILE = "strings-cn.xml";
	public static final String LOCAL_STRING_EN_FILE = "strings-en.xml";
	public static final String LOCAL_STRING_TW_FILE = "strings-tw.xml";
	public static final String LOCAL_STRING_KEY_RESOURCE = "resources";
	public static final String LOCAL_STRING_KEY_STRING = "string";
	public static final String LOCAL_STRING_LANG_TYPE = "LangType";
	public static final int LOCAL_STRING_TYPE_CN = 1;
	public static final int LOCAL_STRING_TYPE_EN = 2;
	public static final int LOCAL_STRING_TYPE_TW = 3;

	// for View Type
	public static final String SIMO_VIEW_CORE_VIEW_TYPE = "ViewType"; // key
	public static final int SIMO_VIEW_BUTTON = 0;
	public static final int SIMO_VIEW_TEXTVIEW = 1;
	public static final int SIMO_VIEW_ACTION = 2; // used for call native
													// ViewController function
													// for special
	public static final int SIMO_VIEW_TEXTEDIT = 3;
	public static final int SIMO_VIEW_SEARCHBAR = 4;
	public static final int SIMO_VIEW_IMAGEVIEW = 5;
	public static final int SIMO_VIEW_UIVIEW = 6;
	public static final int SIMO_VIEW_TEXTVIEW_EDIT = 7;

	// for hide/show animation style
	public static final int SIMO_VIEW_ANI_NONE = 0;
	public static final int SIMO_VIEW_ANI_CURVEEASE_INOUT = 1;
	public static final int SIMO_VIEW_ANI_GRADUALLY = 2;

	// for Touch Type
	public static final String SIMO_VIEW_CORE_TOUCH_TYPE = "TouchType"; // key
																	// 1)
	public static final int SIMO_TOUCH_TouchDragInside = 1 << 2;
	public static final int SIMO_TOUCH_TouchDragOutside = 1 << 3;
	public static final int SIMO_TOUCH_TouchDragEnter = 1 << 4;
	public static final int SIMO_TOUCH_TouchDragExit = 1 << 5;
	public static final int SIMO_TOUCH_TouchUpInside = 1 << 6;
	public static final int SIMO_TOUCH_TouchUpOutside = 1 << 7;
	public static final int SIMO_TOUCH_TouchCancel = 1 << 8;
	public static final int SIMO_TOUCH_TouchLong = 1 << 9;

	// for widget'field key
	public static final String SIMO_VIEW_CORE_VISIBLE = "Visble";
	public static final int SIMO_VIEW_VISIBLE = 0;
	public static final int SIMO_VIEW_INVISIBLE = 4;
	public static final int SIMO_VIEW_GONE = 8;
	public static final int SIMO_VIEW_DISABLE = 16;
	public static final int SIMO_VIEW_ENABLE = 32;

	public static final String SIMO_VIEW_CORE_TEXT = "Text";

	// for insert table view cell animation style
	// TableViewAni
	public static final int TableViewRowAnimationFade = 0;
	public static final int TableViewRowAnimationRight = 1;
	public static final int TableViewRowAnimationLeft = 2;
	public static final int TableViewRowAnimationTop = 3;
	public static final int TableViewRowAnimationBottom = 4;
	public static final int TableViewRowAnimationNone = 5;
	public static final int TableViewRowAnimationMiddle = 6;
	public static final int TableViewRowAnimationAutomatic = 100;

	// TableViewReloadAction
	public static final int TableViewInsert = 0;
	public static final int TableViewDelete = 1;
	public static final int TableViewReload = 2;

	// TableViewScrollPosition
	public static final int TableViewScrollPositionNone = 0;
	public static final int TableViewScrollPositionTop = 1;
	public static final int TableViewScrollPositionMiddle = 2;
	public static final int TableViewScrollPositionBottom = 3;

	// RingType
	public static final int RingCall = 0;
	public static final int RingSMS = 1;

	// RingState
	public static final int RingStart = 0;
	public static final int RingStop = 1;
	public static final int RingPause = 2;

	// for action
	public static final String SIMO_VIEW_CORE_ACTION = "ActionTYPE";
	public static final String SIMO_VIEW_CORE_SUB_ACTION = "SubAction";
	public static final String SIMO_VIEW_CORE_ACTION_VIEW_TOUCHED = "ViewTouched";
	public static final String SIMO_VIEW_CORE_UPDATE_UI = "updateui";
	public static final String SIMO_VIEW_CORE_BUTTON_CLICKED = "clicked";
	public static final String SIMO_VIEW_SET_APPLICATION_FRAME = "SetScreenFrame";
	public static final String SIMO_VIEW_SET_APPLICATION_PATH = "SetAppPath";
	public static final String SIMO_VIEW_SET_USER_INFO = "SetUserInfo";
	public static final String SIMO_VIEW_SET_APPLICATION_LANG = "SetAppLang";
	public static final String SIMO_VIEW_GET_TABLE_SECTION_COUNT = "GetTableSectionCount";
	public static final String SIMO_VIEW_GET_TABLE_SECTION_ARRAY = "GetTableSectionArray";
	public static final String SIMO_VIEW_GET_TABLE_SECTION_TITLE = "GetTableSectionTitle";
	public static final String SIMO_VIEW_GET_TABLE_ROWS = "GetTableRows";
	public static final String SIMO_VIEW_GET_TABLE_MORE = "GetTableMore";
	public static final String SIMO_VIEW_GET_TABLE_CELL = "GetTableCell";
	public static final String SIMO_VIEW_TABLE_CELL_CLICKED = "TableCellClick";
	public static final String SIMO_VIEW_UPDATE_TABLEVIEW = "UpDateTable";
	public static final String SIMO_VIEW_SET_TABLE_FRAME = "SetTableFrame";
	public static final String SIMO_VIEW_SELECT_TABLE_ROW = "SelectTableRow";
	public static final String SIMO_VIEW_TEXT_VIEW_DID_CHANGE = "TextViewDidChange";
	public static final String SIMO_VIEW_TEXT_VIEW_BEGIN_EDITING = "TextViewBeginEdit";

	public static final String SIMO_VIEW_ACTION_SHEET_CLICKED = "ASheetClicked";
	public static final String SIMO_VIEW_ACTION_SHEET_CANCELL = "ASheetCancell";
	public static final String SIMO_VIEW_ALERT_VIEW_CLICKED = "ATViewClicked";
	public static final String SIMO_VIEW_GET_PICK_VIEW_COL = "GetPVCol";
	public static final String SIMO_VIEW_GET_PICK_VIEW_ROW = "GetPVRow";
	public static final String SIMO_VIEW_GET_PICK_VIEW_ROW_DATA = "GetPVRowData";
	public static final String SIMO_VIEW_GET_PICK_VIEW_SELECTED = "GetPVSelected";

	public static final String SIMO_VIEW_RELOAD_TABLE = "ReloadTable";
	public static final String SIMO_VIEW_RELOAD_TABLE_AT_INDEXPATH = "ReloadTableAtIndexPath";
	public static final String SIMO_VIEW_SCROLL_TABLE_AT_INDEXPATH = "ScrollTableAtIndexPath";
	public static final String SIMO_VIEW_UPDATE_SIGNEDBUTTON_AND_TABLECACHE = "UpdateSignedButtonAndTableCache";

	public static final String SIMO_VIEW_SHOW_VIEW = "ShowView";
	public static final String SIMO_VIEW_DISMISS_VIEW = "DismissView";
	public static final String SIMO_VIEW_APP_ACTION = "AppAction";
	public static final String SIMO_VIEW_TEXT_EDITSTART = "TextEditStart";
	public static final String SIMO_VIEW_TEXT_EDIT_CHANGE = "TextEditChange";
	public static final String SIMO_VIEW_TEXT_EDITEND = "TextEditEnd";
	public static final String SIMO_VIEW_SEARCHBAR_EDITSTART = "SearchBarStart";
	public static final String SIMO_VIEW_SEARCHBAR_EDITEND = "SearchBarEnd";
	public static final String SIMO_VIEW_SEARCHBAR_SEARCH_CLICKED = "SearchBarSearch";
	public static final String SIMO_VIEW_SEARCHBAR_SEARCH_CANCELL = "SearchBarCancell";
	public static final String SIMO_VIEW_SEARCHBAR_SEARCH_TEXTCHANGE = "SearchBarTextChange";

	public static final String SIMO_APP_NOTIFY_MSG_ACTION = "NotifyAppMsg";
	public static final String SIMO_APP_SET_DEVICEINFO_ACTION = "SetDeviceInfo";
	public static final String SIMO_APP_REPORT_CLIENT_SLEEP  = "ReportSleepState";
	public static final String SIMO_APP_HOME_VIEW_SWITCH_CHANGE ="SwitchChangeAction";
	public static final String SIMO_APP_HOME_VIEW_SWITCH_KEY="SwitchKey";
	public static final String SIMO_APP_HOME_VIEW_SWITCH_ISCHECKED="SwitchIsChecked";
	// for SIMO APP NOTIFY MSG KEY
	public static final String SIMO_APP_NOTIFY_CONNTECTION = "notifyMsgConnection";
	public static final String SIMO_APP_NOTIFY_ADDRESS_SYNC = "notifyMsgAddressSyn";

	// for SIMO_VIEW_APP_ACTION
	public static final String SIMO_VIEW_APP_ACTION_SHOW_CALLING = "ShowCalling";
	public static final String SIMO_VIEW_APP_ACTION_DISS_CALLING = "DissCalling";


	public static final String SIMO_APP_RING_ACTION = "ringAction";
	public static final String SIMO_APP_RING_TYPE = "ringType";
	public static final String SIMO_APP_RING_STATUE = "ringState";
	public static final String SIMO_APP_RING_FROM_WHO = "ringFrom";

	public static final String SIMO_APP_ACTION_DISMISS_REPORT = "dismissReport";
	public static final String SIMO_APP_ACTION_REPORT_PIN = "reportPin";
	public static final String SIMO_APP_ACTION_REPORT_PUK = "reportPuk";
	public static final String SIMO_APP_KEY_REPORT_KEY = "reportCount";
	// for OTA
	public static final String SIMO_APP_ACTION_OTA_START_REPLY_ACTION = "otaStartAction";
	public static final String SIMO_APP_ACTION_OTA_START_REPLY_TYPE = "type";
	public static final String SIMO_APP_ACTION_OTA_END_REPLY_ACTION = "otaEndAction";
	public static final String SIMO_APP_ACTION_OTA_END_REPLY_TYPE = "type";
	public static final String SIMO_APP_ACTION_OTA_END_REPLY_FILE_LEN = "file_len";
	public static final String SIMO_APP_ACTION_OTA_END_REPLY_END = "end";
	
	public static final short OTA_END_NO = 0; //!<OTA文件传送失败。
	public static final short OTA_END_YES = 1; //!<OTA文件传送成功。
	
	//for report sn / versions
	public static final String SIMO_APP_ACTION_REPORT_SN_VERSIONS = "reportSnVersions";
	public static final String SIMO_APP_KEY_REPORT_SN_KEY = "gmateSN";
	public static final String SIMO_APP_KEY_REPORT_V76_KEY = "gmate76Ver";
	public static final String SIMO_APP_KEY_REPORT_V52_KEY = "gmate52Ver";
	public static final String SIMO_APP_ACTION_REPORT_GMATE_VERSION = "reportGmateVersion";
	public static final String SIMO_APP_KEY_REPORT_GMATE_VER_KEY = "gmateVer";

	// for GMate log
	public static final String SIMO_APP_ACTION_READ_GMATE_LOG = "readGmateLog";
	public static final String SIMO_APP_ACTION_READ_GMATE_LOG_SN = "gameSn";
	public static final String SIMO_APP_ACTION_QUERY_GMATE_LOG_SATE = "queryLogState";
	public static final String SIMO_APP_ACTION_REPORT_GMATE_LOG_STATE = "reportLogState"; //!<接收到了gmate上的日志文件的状态。
	public static final String SIMO_APP_KEY_GMATE_LOG_STATE = "logState";
	public static final String SIMO_APP_KEY_GMATE_LOG_LENGTH = "logLength";
	public static final String SIMO_APP_ACTION_REPORT_ACCOUNT_INFO = "reportAccountInfo";
	public static final String SIMO_APP_KEY_ACCOUNT = "account";
	public static final String SIMO_APP_KEY_MCC     = "mcc";
	
	// for switching tab
	public static final String SIMO_APP_ACTION_SWITCH_TAB = "switchTab";
	public static final String SIMO_APP_KEY_CURRENT_SELECT = "currectSel";

	// for custom pin/puk input
	public static final String SIMO_VIEW_APP_KEY_PIN = "appKeyPin";
	public static final String SIMO_VIEW_APP_KEY_PUK = "appKeyPuk";
	public static final String SIMO_VIEW_APP_REPORT_PIN = "appKeyReportPin";
	public static final String SIMO_VIEW_APP_REPORT_PUK_PIN = "appKeyReportPukPin";

	// for event
	public static final String SIMO_VIEW_EVENT_TYPE = "eventtype";
	public static final int SIMO_VIEW_EVENT_TOUCHDOWN_INSIDE = 1;
	public static final int SIMO_VIEW_EVENT_TOUCHUP_INSIDE = 2;

	// for View Action
	public static final String SIMO_VIEW_CREATE = "onCreate";
	public static final String SIMO_VIEW_WILL_SHOW = "onShow";
	public static final String SIMO_VIEW_WILL_HIDE = "onHide";
	public static final String SIMO_VIEW_WILL_DESTORY = "onDestory";

	// other key
	public static final String SIMO_VIEW_CORE_VIEW_KEY = "vkey";
	public static final String SIMO_VIEW_CORE_KEY = "key";
	public static final String SIMO_VIEW_CORE_ANI_STYLE = "AnimationStyle";
	public static final String SIMO_VIEW_APP_ACTION_SUB_ACTION = "AppSubAction";
	public static final String SIMO_VIEW_DATA_PATH = "DataPath";
	public static final String SIMO_VIEW_DATABASE_PATH = "DBPath";
	public static final String SIMO_VIEW_LOCALSTRING_PATH = "LocalStringPath";
	public static final String SIMO_VIEW_BUTTON_KEY = "Button";
	public static final String SIMO_VIEW_LABLE_KEY = "LABLE";
	public static final String SIMO_VIEW_POINT_X = "PointX";
	public static final String SIMO_VIEW_POINT_Y = "PointY";
	public static final String SIMO_VIEW_WIDTH = "Width";
	public static final String SIMO_VIEW_HEIGHT = "Height";
	public static final String SIMO_VIEW_TABLE_SECTION = "Section";
	public static final String SIMO_VIEW_TABLE_SECTION_ARRAY = "SectionArray";
	public static final String SIMO_VIEW_TABLE_SECTION_TITLE = "SectionTitle";
	public static final String SIMO_VIEW_TABLE_ROW = "TableRow";
	public static final String SIMO_VIEW_TEXT_COLOR = "TextColor";
	public static final String SIMO_VIEW_TEXT_SIZE = "TextSize";
	public static final String SIMO_VIEW_BACKGROUND_COLOR = "BGColor";
	public static final String SIMO_VIEW_BACKGROUND_IMAGE = "BGImage";
	public static final String SIMO_VIEW_IMAGE = "Image";
	public static final String SIMO_VIEW_USE_LOCAL_STRING = "ULocal";
	public static final String SIMO_VIEW_IS_EDITTING = "Editting";
	public static final String SIMO_VIEW_PARENT_VIEW_KEY = "PVKey";
	public static final String SIMO_VIEW_SET_TABLE_PAGE_RESULT = "setTablePageResult";
	public static final String SIMO_VIEW_TEXTVIEW_TEXT = "textViewText";

	public static final String SIMO_TABLEVIEW_CELL_ROW = "tableViewCellRow";
	public static final String SIMO_TABLEVIEW_CELL_SECTION = "tableViewCellSection";
	public static final String SIMO_TABLEVIEW_CELL_ACTION = "tableViewCellAction";
	public static final String SIMO_TABLEVIEW_CELL_APPEAR_ANIMATION = "tableViewCellAppearAnimation";
	public static final String SIMO_TABLEVIEW_SCROLL_POSITION = "tableViewScrollPos";
	public static final String SIMO_TABLEVIEW_SCROLL_ANIMATION = "tableViewScrollAni";

	public static final String SIMO_APP_NOTIFY_MSG_KEY = "key";
	public static final String SIMO_APP_NOTIFY_MSG_MSG = "message";

	// for Dilog Base
	public static final String SIMO_VIEW_DILOG_TITLE = "Title";
	public static final String SIMO_VIEW_DILOG_BTN_CANCELL = "BtnCancel";
	public static final String SIMO_VIEW_DILOG_BUTTON_PREFIX = "Btn";
	public static final String SIMO_VIEW_DILOG_BUTTON0 = "Btn0";
	public static final String SIMO_VIEW_DILOG_BUTTON1 = "Btn1";
	public static final String SIMO_VIEW_DILOG_BUTTON2 = "Btn2";
	public static final String SIMO_VIEW_DILOG_BUTTON3 = "Btn3";
	public static final String SIMO_VIEW_DILOG_BUTTON4 = "Btn4";

	// ShowActionSheet key
	public static final String SIMO_VIEW_ACTION_SHEET_BTNINDEX = "ASBtnIndex";
	// DEVICE INFO KEY
	public static final String SIMO_APP_DEVICE_MODEL_KEY = "DeviceModel";
	public static final String SIMO_APP_DEVICE_UUID_KEY = "DeviceUUID";
	public static final String SIMO_APP_DEVICE_MAC_ADDR = "DeviceMacAddr";
	
	// Report Client Sleep Key
	public static final String SIMO_APP_CLIENT_SLEEP_KEY = "ClientSleep";
	
	// AlertView key
	public static final String SIMO_VIEW_ALERT_VIEW_MESSAGE = "ATTMsg";
	public static final String SIMO_VIEW_ALERT_VIEW_BTNINDEX = "ATBtnIndex";
	public static final String SIMO_VIEW_ALERT_MSG_SPLITTER = "|";

	// PickerView key
	public static final String SIMO_VIEW_PICK_VIEW_COL = "PVCol";
	public static final String SIMO_VIEW_PICK_VIEW_ROW = "PVRow";
	public static final String SIMO_VIEW_PICK_VIEW_DATA = "PVData";

	// for socket engine or bluetooth engine and wifi engine
	public static final int ENGINE_TYPE_BT = 0;
	public static final int ENGINE_TYPE_TCP = 0;
	public static final int ENGINE_TYPE_VOIP = 2;

	public static final String SIMO_NATIVE_ACTION_ENGINE = "Engine";
	public static final String SIMO_NATIVE_ENGINE_TYPE = "EngineType";
	public static final String SIMO_NATIVE_ENGINE_CLIENT_ID = "ClientID";
	public static final String SIMO_NATIVE_ENGINE_FD = "EngineFD";
	public static final String SIMO_NATIVE_ENGINE_PARM1 = "EngineParm1";
	public static final String SIMO_NATIVE_ENGINE_PARM2 = "EngineParm2";
	public static final String SIMO_NATIVE_ENGINE_START_CONNECT = "EngineConnect";
	public static final String SIMO_NATIVE_ENGINE_DISCONNECT = "EngineDisConnect";

	// for Search Device and connect change
	public static final String SIMO_DEVICE_ACTION_ADD_NEW = "AddDevice";
	public static final String SIMO_DEVICE_ACTION_CONNECT_CHANGE = "Connect_Change";
	public static final String SIMO_DEVICE_NAME = "name";
	public static final String SIMO_DEVICE_ADDR = "Addr";
	public static final String SIMO_DEVICE_TYPE = "Type";
	public static final String SIMO_DEVICE_SIG = "Sig";
	public static final String SIMO_CONNECT_STATE = "Connect_State";
	public static final String SIMO_DEVICE_DATATYPE  = "DataType";
	// wifi state
	public static final String WIFI_STATE_CONNECTED = "WIFI_STATE_CONNECTED";
	public static final String WIFI_STATE_DISCONNECTED = "WIFI_STATE_DISCONNECTED";
	 
	// for syncContactToGmate
	public static final String SIMO_ACTION_RELOAD_AFTER_SYNC = "ReloadSync";
	public static final String SIMO_ACTION_SYNC_CONTACT = "SyncContact";
	public static final String SIMO_SYNC_CONTACT_NAME = "Name";
	public static final String SIMO_SYNC_CONTACT_NUMBER = "Number";
	public static final String SIMO_SYNC_CONTACT_NUMBER_TYPE = "Type";

	// notification
	public static final String SIMO_VIEW_APP_ACTION_SHOW_NOTIFICATION = "ShowNotificationView";
	public static final String SIMO_VIEW_APP_ACTION_SHOW_NOTIFICATION_TYPE = "Type";
	public static final String SIMO_VIEW_APP_ACTION_SHOW_NOTIFICATION_STATUS = "Status";
	public static final String SIMO_VIEW_APP_ACTION_SHOW_NOTIFICATION_SIM_ID = "simId";
	public static final String SIMO_VIEW_APP_ACTION_SHOW_NOTIFICATION_SIGNAL = "Signal";
	public static final String SIMO_VIEW_APP_ACTION_SHOW_NOTIFICATION_BATTERY = "Battery";
	public static final String SIMO_VIEW_APP_ACTION_SHOW_NOTIFICATION_COUNT = "Count";

	public static final int NOTIFICATION_SILENT = 0;
	public static final int NOTIFICATION_SOUND = 1;

	// notification call
	public static final String SIMO_VIEW_APP_ACTION_SHOW_NOTIFICATION_CALL = "ShowNotificationCallView";
	public static final String SIMO_VIEW_APP_ACTION_SHOW_NOTIFICATION_CALL_TIME = "Time";
	public static final String SIMO_VIEW_APP_ACTION_SHOW_NOTIFICATION_CALL_NUMBER = "Number";

	// dissmiss notification call
	public static final String SIMO_VIEW_APP_ACTION_DISS_NOTIFICATION_CALL = "DissNotificationCallView";

	//Exception log file name
	public static final String EXCEPTION_FILE = "lanime_exception_log.txt"; //!<例外的日志文件名，这个日志文件不删除。
	

	public static final String LOG_BASE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"com.stupidbeauty.lanime"+File.separator+"Log"; //!<日志目录的路径。
	public static final String LOG_DIR_FILE = "LanIme"+File.separator+"Log";
	public static final String LOG_BASE_ZIP_DIR = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"LanIme"+File.separator+"Log.zip";	
	
	
	public static final String LOG_ANDROID_DIR = "ANDROID";
	
	public static final int CONNECT_STATE_DISCONNECTED = 0;
	public static final int CONNECT_STATE_CONNECTTING = 1;
	
	
	//data connected type
	public static final int DATA_CONNECT_STATE_NO_DATA = 0; //!<当前无数据连接。
	public static final int DATA_CONNECT_STATE_3G = 1; //!<当前连接到移动数据连接。
	public static final int DATA_CONNECT_STATE_WIFI = 2;  //!<当前连接到无线網。
} //public class SimoBaseDef 


