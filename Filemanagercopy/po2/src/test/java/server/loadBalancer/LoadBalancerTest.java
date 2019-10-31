package server.loadBalancer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "server.*")
public class LoadBalancerTest
    {
    Path path;
    ServerSaveData loadBalancer;
    ServerSave task;
    ServerSaveTask dataForTask;

    @Before
    public void setUp() throws Exception
        {
        dataForTask = new ServerSaveTask("user", "file", null);

        path = Paths.get(System.getProperty("user.home")).resolve("test");

        loadBalancer = new ServerSaveData(new ArrayList<Path>()
            {{
            add(path);
            }});

        task = mock(ServerSave.class);
        whenNew(ServerSave.class).withArguments(any(LinkedBlockingQueue.class), any(PriorityBlockingQueue.class)).thenReturn(task);
        }

    @Test
    public void shouldCreateOneSaveHandler() throws Exception
        {
        loadBalancer.saveFile(dataForTask);
        verifyNew(ServerSave.class).withArguments(any(LinkedBlockingQueue.class),
                any(PriorityBlockingQueue.class));
        }

    @Test
    public void shouldReturn2AsPriorityBecauseUserSaveFileTwoTimes()
        {
        loadBalancer.saveFile(dataForTask);
        loadBalancer.saveFile(dataForTask);
        Assert.assertThat(dataForTask.getPriority(), is(2));
        }

    @Test
    public void shouldReturn0AsPriority()
        {
        loadBalancer.saveFile(dataForTask);
        Assert.assertThat(dataForTask.getPriority(), is(1));
        }


    @Test
    public void shouldReturn2AsCount()
        {
        loadBalancer.getCount("user");
        loadBalancer.getCount("user");
        Integer count = loadBalancer.getCount("user");
        Assert.assertThat(count, is(2));
        }


    @Test
    public void shouldReturnFalseBecauseUser1HasBiggerPriority()
        {
        ServerSaveTask data1 = new ServerSaveTask("user", "file", null);
        ServerSaveTask data2 = new ServerSaveTask("user", "file", null);
        ServerSaveTask data3 = new ServerSaveTask("user1", "file", null);

        loadBalancer.saveFile(data1);
        loadBalancer.saveFile(data2);
        loadBalancer.saveFile(data3);

        Assert.assertFalse(data3.compareTo(data2) > 0);
        }

    @Test
    public void shouldReturnFalseBecauseUser1DoesntHaveBiggerPriority()
        {
        ServerSaveTask data1 = new ServerSaveTask("user", "file", null);
        ServerSaveTask data2 = new ServerSaveTask("user", "file", null);
        ServerSaveTask data3 = new ServerSaveTask("user1", "file", null);
        ServerSaveTask data4 = new ServerSaveTask("user1", "file", null);
        ServerSaveTask data5 = new ServerSaveTask("user1", "file", null);

        loadBalancer.saveFile(data1);
        loadBalancer.saveFile(data2);
        loadBalancer.saveFile(data3);
        loadBalancer.saveFile(data4);
        loadBalancer.saveFile(data5);


        Assert.assertFalse(data5.compareTo(data2) < 0);
        }
    }
