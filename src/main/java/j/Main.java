package j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static j.Event.Event;
import static j.FormResult.FormResult;

public class Main {

    static class Device1 implements EventSource {
    }

    static class Device2 implements EventSource {
    }

    public static void main(String[] args) {
        System.out.println("CASE CLASSES");
        EventSource d1 = new Device1();

        long currentTime = System.currentTimeMillis();
        Event ev1 = Event(1, "event1", currentTime, d1);
        Event ev2 = Event(2, "event2", System.currentTimeMillis(), new Device2());
        Event ev3 = Event(3, "event3", System.currentTimeMillis(), d1);

        System.out.println(ev1.equals(Event(1, "event1", currentTime, d1)));
        System.out.println(ev3);

        System.out.println();
        System.out.println("PATTERN MATCHING");
        List<Event> queue = new ArrayList(Arrays.asList(ev1, ev2, ev3));
        handleEvents(queue);

        System.out.println(genericSize("test"));
        System.out.println(genericSize(new int[]{1, 2, 3}));
        System.out.println(genericSize(new ArrayList(Arrays.asList(1, 2, 3))));
        System.out.println(genericSize(new ObjectWithSize()));
        System.out.println(genericSize(new Object()));

        System.out.println();
        System.out.println("COLLECTIONS");

        List<Integer> collection = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
            collection.add(i);
        }

        int sum = 0;
        for (int n : collection) {
            sum += n;
        }
        System.out.println("sum: " + sum);

        List<Integer> odds = new ArrayList<Integer>();
        for (int n : collection) {
            if (n % 2 == 1) {
                odds.add(n);
            }
        }
        System.out.println("Number of odds: " + odds.size());

        System.out.println();
        System.out.println("OWN CONTROL STRUCTURES");
        Transaction t1 = new Transaction();
        t1.begin();
        try {
            System.out.println("I am in transaction, safe and sound!");
            t1.commit();
        } catch (Exception ex) {
            t1.rollback();
        }

        Transaction t2 = new Transaction();
        t2.begin();
        try {
            System.out.println("I am in transaction that is going to fail!");
            throwEx();
            t2.commit();
        } catch (Exception ex) {
            t2.rollback();
        }

        System.out.println();
        System.out.println("OPTION TYPE");
        String inputName = "George";
        String inputSurname = "Smith";
        Integer inputAge = 23;
        Boolean inputTermsAgreed = true;
        FormResult result1 = null;
        if (inputName != null && inputSurname != null && inputAge != null && inputTermsAgreed != null) {
            result1 = FormResult(inputName, inputSurname, inputAge, inputTermsAgreed);
        }
        System.out.println(result1);

        inputTermsAgreed = null;

        FormResult result2 = null;
        if (inputName != null && inputSurname != null && inputAge != null && inputTermsAgreed != null) {
            result2 = FormResult(inputName, inputSurname, inputAge, inputTermsAgreed);
        }
        System.out.println(result2);
    }

    private static void handleEvents(List<Event> queue) {
        if (queue.isEmpty()) {
            System.out.println("All events handled.");
        } else {
            processEvent(queue.get(0));
            handleEvents(queue.subList(1, queue.size()));
        }
    }

    private static void processEvent(Event ev) {
        System.out.println("Processing event: " + ev);
        if (ev.getName().equals("event2")) {
            System.out.println("'event2' from device " + ev.getSource());
        } else if (ev.getWhen() > System.currentTimeMillis() - 10000) {
            System.out.println("fresh event");
        } else {
            // ignore
        }
    }

    private static void throwEx() {
        throw new RuntimeException("fail");
    }

    private static int genericSize(Object obj) {
        if (obj instanceof String) {
            return ((String) obj).length();
        } else if (obj instanceof int[]) {
            return ((int[]) obj).length;
        } else if (obj instanceof List) {
            return ((List) obj).size();
        } else if (obj instanceof ObjectWithSize) {
            return ((ObjectWithSize) obj).count();
        } else {
            return -1;
        }
    }
}
