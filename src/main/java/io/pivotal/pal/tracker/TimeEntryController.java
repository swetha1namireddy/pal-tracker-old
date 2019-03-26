package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {


    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(@Autowired TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable(name= "id") Long id )
    {
        TimeEntry t = timeEntryRepository.find(id);
        if(t == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(t);
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry)
    {
        TimeEntry t = timeEntryRepository.create(timeEntry);

        return ResponseEntity.status(HttpStatus.CREATED).body(t);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list()
    {
        List<TimeEntry> t = timeEntryRepository.list();

        return ResponseEntity.status(HttpStatus.OK).body(t);
    }


    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long timeEntryId, @RequestBody TimeEntry timeEntry) {

            TimeEntry updated = timeEntryRepository.update(timeEntryId,timeEntry );
            if(updated == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(updated);

    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
