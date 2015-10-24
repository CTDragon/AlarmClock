package com.better.alarm;

import android.content.Context;
import android.database.Cursor;

import com.better.alarm.model.AlarmCore;
import com.better.alarm.model.AlarmStateNotifier;
import com.better.alarm.model.AlarmsScheduler;
import com.better.alarm.model.IAlarmContainer;
import com.better.alarm.model.IAlarmsScheduler;
import com.better.alarm.model.persistance.AlarmContainer;
import com.github.androidutils.logger.Logger;
import com.google.common.base.Preconditions;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yuriy on 24.10.2015.
 */
@Module
public class AlarmClockModule {
    private final Context mContext;

    public AlarmClockModule(Context context) {
        mContext = Preconditions.checkNotNull(context);
    }

    @Provides
    public IAlarmCoreFactory container(final Logger logger, final Context context, final IAlarmsScheduler alarmsScheduler, final AlarmCore.IStateNotifier broadcaster) {
        return new IAlarmCoreFactory() {
            @Override
            public AlarmCore fromCursor(Cursor cursor) {
                return new AlarmCore(new AlarmContainer(cursor, logger, context), context, logger, alarmsScheduler, broadcaster);
            }

            @Override
            public AlarmCore fromScratch() {
                return new AlarmCore(new AlarmContainer(logger, context), context, logger, alarmsScheduler, broadcaster);
            }
        };
    }

    @Provides
    @Singleton
    public Context context() {
        return mContext;
    }

    @Provides
    @Singleton
    public Logger logger() {
        return Logger.getDefaultLogger();
    }

    @Provides
    @Singleton
    public IAlarmsScheduler alarmsScheduler(Logger logger, Context context) {
        return new AlarmsScheduler(context, logger);
    }

    @Provides
    @Singleton
    public AlarmCore.IStateNotifier broadcaster(Context context) {
        return new AlarmStateNotifier(context);
    }
}
