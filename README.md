# rockjob
Library to run jobs.

To create new job your class should implement `RockJobRunnable` interface. 
Also mark your class with `@RunJob` annotation with `jobTitle` unique name. 
To run job asynchronously use attribute `anyc` in `@RunJob` annotation.
```
@RockJob(jobTitle = "rock", async = false)
public class TestRockJob implements RockJobRunnable {
    public void runJob() throws Exception {
        // .. Here is the code of your job
    }
}
```
To run job `RockJobManager` bean should be autowired:

```
@Autowired
RockJobManager rockJobManager;
```

Ask `RockJobManager` to run job with `jobTitle`:
```
rockJobManager.runJob("rock");
```
