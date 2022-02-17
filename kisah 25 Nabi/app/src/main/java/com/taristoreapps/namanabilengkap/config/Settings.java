package com.taristoreapps.namanabilengkap.config;

public class Settings {


    public static final String FOLDER_ID ="1Z99nVxIgPneJi4ZAQum9BpXqXKIuCg0I";
    public static final String API_KEY="AIzaSyDmzeKPAb2WATJIzKMUZlhFZ_-M4H7WBK4";

    /*
   Please upload wallgdrive.json to your google drive
   and get FILE_ID = JSON_ID
    */
    public static final String JSON_ID="1v77c6P7-674mlxLndG7NM9ovHNepm8mA";


    /*
   ON_OFF_ADS ="0" offline ads, get data from Settings.java
   ON_OFF_ADS ="1" online ads, get data from url json
    */
    public static String ON_OFF_ADS = "1";

    /*
     SELECT_ADS="ADMOB"
     SELECT_ADS="APPLOVIN-M" for MAx and SELECT_ADS="APPLOVIN-D" for Discovery
     SELECT_ADS="STARTAPP"
     SELECT_ADS="MOPUB"
     SELECT_ADS="IRON"
     SELECT_ADS="FACEBOOK"
     SELECT_ADS="UNITY"
     SELECT_ADS="GOOGLE-ADS"

     Admob can't bakup with Google Ads Manager
    */
    public static String SELECT_ADS = "ADMOB";

    /*
      SELECT_ADS="APPLOVIN-M" for MAx and SELECT_ADS="APPLOVIN-D" for Discovery
      SELECT_BACKUP_ADS="STARTAPP"
      SELECT_BACKUP_ADS="MOPUB"
      SELECT_BACKUP_ADS="IRON"
      SELECT_BACKUP_ADS="ADMOB"
      SELECT_BACKUP_ADS="FACEBOOK"
      SELECT_BACKUP_ADS="UNITY"
      SELECT_BACKUP_ADS="GOOGLE-ADS"
     */

    public static String SELECT_BACKUP_ADS = "APPLOVIN-D";


    /*Admob ID and Applovin SDK Key
    please check string.xml
    Admob = replace <string name="admobappid">ca-app-pub-3940256099942544~3347511713</string>
    Applovin = replace <string name="sdk_key_applovin">5UhA2fX7QnsiNAwdmPdJW-QTCSqDx1ssvQm1QC252VUsD0sLAG1-2hpDaqHKsM3ZwH0RlcTNcLKTn-gB_xBeBo</string>
    with your id
     */

    public static String MAIN_ADS_INTER = "ca-app-pub-3940256099942544/1033173712";
    public static String MAIN_ADS_BANNER = "ca-app-pub-3940256099942544/6300978111";

    public static String BACKUP_ADS_INTER = "qwerty1234";
    public static String BACKUP_ADS_BANNER = "b195f8dd8ded45fe847ad89ed1d016da";

    /*
    INITIALIZE_SDK  and INITIALIZE_SDK_BACKUP_ADS uses for MOBUP, STARTAPP and IRONSOURCE
    fill INITIALIZE_SDK or INITIALIZE_SDK_BACKUP_ADS with App Key for IRONSOURCE
    example : INITIALIZE_SDK ="107355779"

    fill INITIALIZE_SDK or INITIALIZE_SDK_BACKUP_ADS with Banner ID for MOPUB
    example : INITIALIZE_SDK ="b195f8dd8ded45fe847ad89ed1d016da"

    fill INITIALIZE_SDK or INITIALIZE_SDK_BACKUP_ADSwith App ID for STARTAPP
    example : INITIALIZE_SDK ="123456789"

    if use Admob and Applovin as main or backup ads please input INITIALIZE_SDK ="" or INITIALIZE_SDK_BACKUP_ADS =""
     */
    public static String INITIALIZE_SDK = ""; //Main Ads
    public static String INITIALIZE_SDK_BACKUP_ADS = ""; // Backup Ads

    /*
    OPEN Ads only for ADMOB
    can't load from json online
     */
    public static String ON_OFF_OPEN_ADS="1";  // "0" = on or "1" = off
    public static String ADMOB_OPENADS = "ca-app-pub-3940256099942544/3419835294";

    /*
    HPK only for Admob and Applovin-D (Discovery)
    please fill with HPK Adsense/Admob like finance, etc or you can ignore it

    uses HPK
    public static String HIGH_PAYING_KEYWORD1="finance";

    ignore HPK
    public static String HIGH_PAYING_KEYWORD1="";
     */
    public static String HIGH_PAYING_KEYWORD1="";
    public static String HIGH_PAYING_KEYWORD2="";
    public static String HIGH_PAYING_KEYWORD3="";
    public static String HIGH_PAYING_KEYWORD4="";
    public static String HIGH_PAYING_KEYWORD5="";

    /*
    STATUS_APP = "0" must set 0 if your app still live on playstore
    and STATUS_APP = "1" if your app "suspend"
    fill LINK_REDIRECT with new link app
    */
    public static String STATUS_APP = "0";
    public static String LINK_REDIRECT = "https://play.google.com/store/apps/details?id=com.ad.tesiqkepribadia&hl=en";

    /*
  Interval for Intertitial ads
   */
    public static int INTERVAL = 5;
    public static boolean CHILD_DIRECT_GDPR = true;
}
