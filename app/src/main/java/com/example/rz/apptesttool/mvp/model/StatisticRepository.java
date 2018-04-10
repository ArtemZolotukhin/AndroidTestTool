package com.example.rz.apptesttool.mvp.model;

import java.util.Collection;

/**
 * Created by rz on 4/10/18.
 */

public interface StatisticRepository {

    /**
     *
     * @param touchInfo
     * @return true if successful
     */
    boolean put(TouchInfo touchInfo);

    /**
     *
     * @param touchInfoCollection
     * @return true if successful
     */
    boolean put(Collection<TouchInfo> touchInfoCollection);

    Collection<TouchInfo> getAllTouchInfo();

    /**
     *
     * clear all touch info data from device
     *
     * @return true if successful
     */
    boolean removeAllTouchInfo();

    /**
     *
     * @param timeInfo
     * @return true if successful
     */
    boolean putTimeInfo(TimeInfo timeInfo);

    /**
     *
     * @param timeInfo
     * @return true if successful
     */
    boolean putTimeInfo(Collection<TimeInfo> timeInfo);

    Collection<TimeInfo> getAllTimeInfo();

    /**
     * clear all time info data from device
     * @return true if successful
     */
    boolean removeAllTimeInfo();

}
