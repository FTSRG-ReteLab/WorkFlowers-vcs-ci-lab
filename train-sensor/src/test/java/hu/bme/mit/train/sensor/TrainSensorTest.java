package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController mockTC;

    TrainUser mockTU;
    TrainSensor trainSensor;

    @Before
    public void before() {
        mockTC = mock(TrainController.class);
        mockTU = mock(TrainUser.class);
        trainSensor= new TrainSensorImpl(mockTC,mockTU);
    }

    @Test
    public void CheckSpeedLimit() {
        Assert.assertEquals(5, trainSensor.getSpeedLimit());
    }

    @Test
    public void SpeedMarginOk() {
        trainSensor.overrideSpeedLimit(120);
        verify(mockTU, times(0)).setAlarmState(true);
    }

    @Test
    public void LotSpeed() {
        trainSensor.overrideSpeedLimit(550);
        verify(mockTU, times(1)).setAlarmState(true);
    }

    @Test
    public void LittleSpeed() {
        trainSensor.overrideSpeedLimit(-10);
        verify(mockTU, times(1)).setAlarmState(true);
    }
    @Test
    public void LessThan50D() {
        trainSensor.overrideSpeedLimit(120);

        trainSensor.overrideSpeedLimit(50);

        verify(mockTU, times(1)).setAlarmState(true);
    }





}
