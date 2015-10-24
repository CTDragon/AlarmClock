package com.better.alarm;

import android.database.Cursor;

import com.better.alarm.model.AlarmCore;

/**
 * Created by Yuriy on 24.10.2015.
 */
public interface IAlarmCoreFactory {
    AlarmCore fromCursor(Cursor cursor);
    AlarmCore fromScratch();
}
