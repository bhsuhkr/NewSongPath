package com.newsong.newsongtime.ui.dashboard;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.SwitchPreference;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.newsong.newsongtime.R;

public class DashboardFragment extends PreferenceFragmentCompat {

    private Preference myPref;
    private PreferenceManager manager;
    private SwitchPreference pushNotificationSwitch;
    private int selectedHourForNotification;
    private int selectedMinuteForNotification;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.setting_activity);
    }
}


//    @Override
//    public void onCreatePreferences(Bundle bundle, String s) {
//        addPreferencesFromResource(R.xml.setting_activity);
//
//        manager = getPreferenceManager();
//        manager.findPreference("pushNotificationOnOff");
//
//        // Retrieve saved time for notification
//        // If it does not exist, default value is 7:00 AM.
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("timeSaved", MODE_PRIVATE);
//        String savedHour = sharedPreferences.getString("hour", "7");
//        String savedMinute = sharedPreferences.getString("minute", "0");
//        selectedHourForNotification = Integer.parseInt(savedHour);
//        selectedMinuteForNotification = Integer.parseInt(savedMinute);
//
//        pushNotificationSwitch = findPreference("pushNotificationOnOff");
//        pushNotificationSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                // checked = false, not checked = true
////                boolean switched = ((SwitchPreference) preference).isChecked();
//
//                if(pushNotificationSwitch.isChecked()){
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.set(Calendar.HOUR_OF_DAY, selectedHourForNotification);
//                    calendar.set(Calendar.MINUTE, selectedMinuteForNotification);
//                    calendar.set(Calendar.SECOND, 1);
//                    if(selectedMinuteForNotification<10){
//                        myPref.setSummary(selectedHourForNotification+":0"+selectedMinuteForNotification);
////                        scheduleNotification(getNotification(selectedHourForNotification+":0"+selectedMinuteForNotification), calendar.getTimeInMillis());
//                        scheduleNotification(triggerNotification(), calendar.getTimeInMillis());
//                    }else{
//                        myPref.setSummary(selectedHourForNotification+":"+selectedMinuteForNotification);
////                        scheduleNotification(getNotification(selectedHourForNotification+":"+selectedMinuteForNotification), calendar.getTimeInMillis());
//                        scheduleNotification(triggerNotification(), calendar.getTimeInMillis());
//                    }
//
//                }else{
//                    // Cancel notification
//                    Intent intent = new Intent(getContext(), NotificationPublisher.class);
//                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0,
//                            intent, PendingIntent.FLAG_CANCEL_CURRENT);
//                    AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
//                    alarmManager.cancel(pendingIntent);
//                }
//                return true;
//            }
//        });
//
//        myPref = findPreference("timePreference");
//        myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            public boolean onPreferenceClick(Preference preference) {
//
//                Calendar currentTime = Calendar.getInstance();
//                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
//                int minute = currentTime.get(Calendar.MINUTE);
//                TimePickerDialog mTimePicker;
//                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                        selectedHourForNotification = selectedHour;
//                        selectedMinuteForNotification = selectedMinute;
//                        if(pushNotificationSwitch.isChecked()){
//                            Calendar calendar = Calendar.getInstance();
//                            calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
//                            calendar.set(Calendar.MINUTE, selectedMinute);
//                            calendar.set(Calendar.SECOND, 0);
//
//                            if (calendar.before(Calendar.getInstance())) {
//                                calendar.add(Calendar.DAY_OF_YEAR, 1);
//                            }
////
////                            if(selectedMinute<10){
//////                                myPref.setSummary(selectedHour+":0"+selectedMinute);
//////                                scheduleNotification(getNotification(selectedHour+":0"+selectedMinute), calendar.getTimeInMillis());
////                                scheduleNotification(triggerNotification(), calendar.getTimeInMillis());
////                            }else{
//////                                myPref.setSummary(selectedHour+":"+selectedMinute);
//////                                scheduleNotification(getNotification(selectedHour+":"+selectedMinute), calendar.getTimeInMillis());
////                                scheduleNotification(triggerNotification(), calendar.getTimeInMillis());
////                            }
//
//                            scheduleNotification(triggerNotification(), calendar.getTimeInMillis());
//
//                            // Save selected time for notification
//                            SharedPreferences userDetails = getContext().getSharedPreferences("timeSaved", MODE_PRIVATE);
//                            SharedPreferences.Editor edit = userDetails.edit();
//                            edit.clear();
//                            edit.putString("hour", ""+selectedHour);
//                            edit.putString("minute", ""+selectedMinute);
//                            edit.commit();
//                            Toast.makeText(getContext(), "저장되었습니다." , Toast.LENGTH_SHORT).show();
////                            Toast.makeText(SettingActivity.this, "Alarm: "+selectedHour + " : " + selectedMinute , Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }, hour, minute, false);
//                mTimePicker.setTitle("시간을 선택하세요.");
//                mTimePicker.show();
//                return false;
//            }
//        });
//
////        if(selectedMinuteForNotification<10){
////            myPref.setSummary(selectedHourForNotification+":0"+selectedMinuteForNotification);
////        }else{
////            myPref.setSummary(selectedHourForNotification+":"+selectedMinuteForNotification);
////        }
//
//    }
//
//    private void scheduleNotification(Notification notification, long futureInMillis) {
//        Intent notificationIntent = new Intent(getContext(), NotificationPublisher.class);
//        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0,
//                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);
////        alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
////        alarmManager.cancel(pendingIntent);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
//    }
//
//    private Notification triggerNotification(){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            Intent intent = new Intent(getContext(), MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0, intent, 0);
//
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "channel1")
//                    .setSmallIcon(R.drawable.logo)
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logo))
//                    .setContentTitle("New Song Path")
//                    .setContentText("오늘의 말씀을 읽으세요.")
//                    .setStyle(new NotificationCompat.BigTextStyle().bigText("오늘의 말씀을 읽으세요."))
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                    .setContentIntent(pendingIntent)
//                    .setChannelId("channel1")
//                    .setAutoCancel(true);
//            return builder.build();
//
//        }else{
//            String message = "오늘의 말씀을 읽으세요.";
//            Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext())
//                    .setSmallIcon(R.drawable.logo)
////                .setContentTitle(content)
//                    .setContentTitle("뉴송 타임")
//                    .setContentText(message)
////                    .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
//                    .setSound(soundUri)
//                    .setAutoCancel(true);
//
//            // Notification Vibrate
////        builder.setDefaults(Notification.DEFAULT_VIBRATE);
//
//            Intent intent = new Intent(getContext(), MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.putExtra("message", message);
//
//            PendingIntent pendingIntent = PendingIntent.getActivity(getContext()
//                    ,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//            builder.setContentIntent(pendingIntent);
//            return builder.build();
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        // Retrieve saved time for notification
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("timeSaved", MODE_PRIVATE);
//        String savedHour = sharedPreferences.getString("hour", "7");
//        String savedMinute = sharedPreferences.getString("minute", "0");
//        selectedHourForNotification = Integer.parseInt(savedHour);
//        selectedMinuteForNotification = Integer.parseInt(savedMinute);
//
//        if(pushNotificationSwitch.isChecked()){
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.HOUR_OF_DAY, selectedHourForNotification);
//            calendar.set(Calendar.MINUTE, selectedMinuteForNotification);
//            calendar.set(Calendar.SECOND, 1);
////            if(selectedMinuteForNotification<10){
////                myPref.set(selectedHourForNotification+":0"+selectedMinuteForNotification);
////            }else{
////                myPref.setSummary(selectedHourForNotification+":"+selectedMinuteForNotification);
////            }
//
//        }else{
////            myPref.setSummary("시간을 선택하세요.");
//
//            // Cancel notification
//            Intent intent = new Intent(getContext(), NotificationPublisher.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0,
//                    intent, PendingIntent.FLAG_CANCEL_CURRENT);
//            AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
//            alarmManager.cancel(pendingIntent);
//        }
//    }