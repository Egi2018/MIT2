package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class TrainSensorTest {

    TrainSensorImpl sensor;
    TrainController controller;
    TrainUser user;

    @Before
    public void before() {
        user = mock(TrainUser.class);
        controller = mock(TrainController.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void overrideSpeedLimit_normal_success() {
        sensor.overrideSpeedLimit(50);
        verify(user, never()).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_lessThan0_alarm() {
        sensor.overrideSpeedLimit(-1);
        verify(user).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_moreThan500_alarm() {
        sensor.overrideSpeedLimit(501);
        verify(user).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_lessThan50percent_alarm() {
        sensor.overrideSpeedLimit(100);
        sensor.overrideSpeedLimit(10);
        verify(user).setAlarmState(true);
    }
}
