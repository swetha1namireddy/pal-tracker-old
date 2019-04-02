package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository  implements TimeEntryRepository {

    Map<Long, TimeEntry> database = new HashMap<Long, TimeEntry>();
    long counter = 0L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        if(timeEntry.getId() == 0) {
            counter ++;
            Long id = counter;
            timeEntry.setId(id);
        }
        database.put(timeEntry.getId(),timeEntry );
        return find(timeEntry.getId());
    }

    @Override
    public TimeEntry find(Long timeEntryId) {
        return database.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return  database.values().stream().collect(Collectors.toList());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        database.put(id,timeEntry );
        return find(id);
    }

    @Override
    public void delete(Long timeEntryId) {
        database.remove(timeEntryId);
    }


}
