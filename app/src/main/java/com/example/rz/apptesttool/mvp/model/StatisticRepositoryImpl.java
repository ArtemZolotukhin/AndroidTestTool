package com.example.rz.apptesttool.mvp.model;

import android.content.Context;
import android.widget.Toast;

import com.example.rz.apptesttool.mvp.model.providers.TimeServiceProvider;
import com.example.rz.apptesttool.mvp.model.providers.TouchServiceProvider;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by rz on 4/10/18.
 */

public class StatisticRepositoryImpl implements StatisticRepository {

    private Realm mRealm;
    private Context context;
    private static StatisticRepositoryImpl instance;

    private StatisticRepositoryImpl (Context context) {
        this.context = context;
        Realm.init(context);
        mRealm = Realm.getDefaultInstance();
    }


    @Override
    public boolean put(TouchInfo touchInfo) {
        mRealm.beginTransaction();
        TouchInfo touchInfo1 = mRealm.createObject(TouchInfo.class);
        touchInfo1.setX(touchInfo.getX());
        touchInfo1.setY(touchInfo.getY());
        touchInfo1.setActivity(touchInfo.getActivity());
        mRealm.commitTransaction();

        ArrayList<TouchInfo> list = (ArrayList<TouchInfo>) getAllTouchInfo();
        if(list.size()%10 == 0) {
            mRealm.beginTransaction();
            //Toast.makeText(context, list.size() + "10 касаний", Toast.LENGTH_SHORT).show();
            TouchService service = TouchServiceProvider.get();
            service.send(list, voidIntegerResponse -> {
                if(voidIntegerResponse.isSuccessfull()){
                    removeAllTouchInfo();
                    //Toast.makeText(context, "10 касаний отправлены", Toast.LENGTH_SHORT).show();
                }
            });
            mRealm.commitTransaction();
        }
        return true;
    }


    public void deleteAllTouchesByActivity(String activity) {
        mRealm.beginTransaction();
        RealmResults<TouchInfo> tests = mRealm.where(TouchInfo.class).contains("activity", activity).findAll();
        tests.deleteAllFromRealm();
        mRealm.commitTransaction();
    }

    @Override
    public boolean put(Collection<TouchInfo> touchInfoCollection) {
        mRealm.beginTransaction();

        TouchInfo touchInfo1 = mRealm.createObject(TouchInfo.class);
        Iterator<TouchInfo> iterator = touchInfoCollection.iterator();
        TouchInfo touchInfo;
        while (iterator.hasNext()) {
            touchInfo = iterator.next();
            touchInfo1.setX(touchInfo.getX());
            touchInfo1.setY(touchInfo.getY());
            touchInfo1.setActivity(touchInfo.getActivity());
            mRealm.commitTransaction();
        }
        //STUB
        return true;
    }

    @Override
    public Collection<TouchInfo> getAllTouchInfo() {
        Collection<TouchInfo> infos = new ArrayList<>();
        RealmResults<TouchInfo> tests = mRealm.where(TouchInfo.class).findAll();
        infos.addAll(tests);
        return infos;
        //STUB
    }


    public Collection<TouchInfo> getAllTouchInfoByActivity(String activityName) {
        Collection<TouchInfo> infos = new ArrayList<>();
        RealmResults<TouchInfo> tests = mRealm.where(TouchInfo.class).contains("activity", activityName).findAll();
        infos.addAll(tests);
        return infos;
        //STUB
    }


    @Override
    public boolean removeAllTouchInfo() {
        mRealm.beginTransaction();
        RealmResults<TouchInfo> tests = mRealm.where(TouchInfo.class).findAll();
        tests.deleteAllFromRealm();
        mRealm.commitTransaction();
        //STUB
        return true;
    }

    @Override
    public boolean putTimeInfo(TimeInfo timeInfo) {
        mRealm.beginTransaction();
        TimeInfo time = getTimeInfoByActivity(timeInfo.getActivity());
        if(time!=null) {
            mRealm.insertOrUpdate(time);
        } else {
            mRealm.insertOrUpdate(timeInfo);
        }
        mRealm.commitTransaction();
        //STUB
        return true;
    }

    public void updateTimeInfo(long time, TimeInfo timeInfo) {
        mRealm.beginTransaction();
        timeInfo.setTime(time);
        mRealm.insertOrUpdate(timeInfo);
        mRealm.commitTransaction();
    }

    public TimeInfo getTimeInfoByActivity(String activityName) {
        TimeInfo tests = mRealm.where(TimeInfo.class).contains("activity", activityName).findFirst();
        return tests;
    }

    @Override
    public boolean putTimeInfo(Collection<TimeInfo> timeInfoCollection) {
        mRealm.beginTransaction();
        TimeInfo timeInfo1 = mRealm.createObject(TimeInfo.class);
        Iterator<TimeInfo> iterator = timeInfoCollection.iterator();
        TimeInfo timeInfo;
        while (iterator.hasNext()) {
            timeInfo = iterator.next();
            timeInfo1.setTime(timeInfo.getTime());
            timeInfo1.setActivity(timeInfo.getActivity());
            mRealm.commitTransaction();
        }
        //STUB
        return true;
    }

    @Override
    public Collection<TimeInfo> getAllTimeInfo() {
        Collection<TimeInfo> infos = new ArrayList<>();
        RealmResults<TimeInfo> tests = mRealm.where(TimeInfo.class).findAll();
        infos.addAll(tests);
        return infos;
    }

    @Override
    public boolean removeAllTimeInfo() {
        mRealm.beginTransaction();
        RealmResults<TimeInfo> tests = mRealm.where(TimeInfo.class).findAll();
        tests.deleteAllFromRealm();
        mRealm.commitTransaction();
        //STUB
        return true;
    }

    public void refreshDataBase() {
        ArrayList<TimeInfo> list = (ArrayList<TimeInfo>) getAllTimeInfo();
        if(list.size()!=0) {
            System.out.println("!!!!!!!!!!!!!!!!!!" + list.size());
            for (int i = 0; i <list.size() ; i++) {
                TimeServiceProvider.get().send(list.get(i), voidIntegerResponse -> {
                    if(voidIntegerResponse.isSuccessfull())
                        removeAllTimeInfo();
                });
            }
        }



    }



    public static StatisticRepositoryImpl getInstance(Context context) {
        if(instance == null) {
            instance = new StatisticRepositoryImpl(context);
        }
        return instance;

    }

}
