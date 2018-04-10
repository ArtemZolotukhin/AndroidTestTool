package com.example.rz.apptesttool.mvp.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by rz on 4/10/18.
 */

public class StatisticRepositoryImpl implements StatisticRepository {


    @Override
    public boolean put(TouchInfo touchInfo) {


        //STUB
        return true;
    }

    @Override
    public boolean put(Collection<TouchInfo> touchInfoCollection) {

        //STUB
        return true;
    }

    @Override
    public Collection<TouchInfo> getAllTouchInfo() {






        //STUB
        Collection<TouchInfo> list = new ArrayList<>(3);
        list.add(new TouchInfo(50, 50, "com.example.rz.apptesttool.temp.MainActivity"));
        list.add(new TouchInfo(100, 100, "com.example.rz.apptesttool.temp.MainActivity"));
        list.add(new TouchInfo(150, 100, "com.example.rz.apptesttool.temp.Main3Activity"));
        return list;
    }

    @Override
    public boolean removeAllTouchInfo() {

        //STUB
        return true;
    }

    @Override
    public boolean putTimeInfo(TimeInfo timeInfo) {


        //STUB
        return true;
    }

    @Override
    public boolean putTimeInfo(Collection<TimeInfo> timeInfo) {


        //STUB
        return true;
    }

    @Override
    public Collection<TimeInfo> getAllTimeInfo() {




        //STUB
        Collection<TimeInfo> list = new ArrayList<>(3);
        list.add(new TimeInfo("com.example.rz.apptesttool.temp.MainActivity", 1000));
        list.add(new TimeInfo("com.example.rz.apptesttool.temp.MainActivity", 8000));
        list.add(new TimeInfo("com.example.rz.apptesttool.temp.MainActivity", 120000));
        return list;
    }

    @Override
    public boolean removeAllTimeInfo() {


        //STUB
        return true;
    }
}
