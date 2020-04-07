package be.helmo.rwid.modelTest;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import be.helmo.rwid.model.EventData;
import be.helmo.rwid.model.PartyStatut;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class EventDataTest {
    @Test
    public void compareToEventById() {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, 8);
        cal.set(Calendar.DAY_OF_MONTH, 17);

        EventData data1 = new EventData(0, "data1", "12", PartyStatut.ON_WAY.toString(), cal);
        EventData data2 = new EventData(1, "data2", "11", PartyStatut.FINISH.toString(), cal);

        assertEquals(0 ,data1.compareTo(data1));
        assertEquals(-1 ,data1.compareTo(data2));
        assertEquals(1,data2.compareTo(data1));
    }

    @Test
    public void equalsEventById() {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, 8);
        cal.set(Calendar.DAY_OF_MONTH, 17);

        EventData data1 = new EventData(0, "data1", "12", PartyStatut.ON_WAY.toString(), cal);
        EventData data2 = new EventData(1, "data2", "11", PartyStatut.FINISH.toString(), cal);
        EventData data3 = new EventData(0, "data3", "10", PartyStatut.FINISH.toString(), cal);

        assertEquals(data1, data1);
        assertEquals(data1, data3);
        assertNotEquals(data2, data1);
    }

    @Test
    public void returnTheGoodDateFormat() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 17);

        EventData data1 = new EventData(0, "data1", "12", PartyStatut.ON_WAY.toString(), cal);

        assertEquals("17/01/2020" ,data1.getDateToString());
    }

    @Test
    public void eventIsFinishOrNot() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, 8);
        cal.set(Calendar.DAY_OF_MONTH, 17);

        EventData data1 = new EventData(0, "data1", "12",PartyStatut.FINISH.toString() , cal);
        assertTrue(data1.isFinish());

        cal.set(Calendar.YEAR, 2400);

        EventData data2 = new EventData(1, "data2", "121", PartyStatut.ON_WAY.toString(), cal);
        assertFalse(data2.isFinish());
    }
}