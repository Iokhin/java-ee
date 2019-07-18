package ru.iokhin.tm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.iokhin.tm.api.ProjectClient;
import ru.iokhin.tm.api.TaskClient;
import ru.iokhin.tm.model.ProjectDTO;
import ru.iokhin.tm.model.TaskDTO;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class TaskClientTest {

    private final static String URL_PROJECT = "http://localhost:8080/api/project";
    private final static String URL_TASK = "http://localhost:8080/api/task";
    private final static String PROJECT_ID = "678f735d-5aa5-228b-ad36-69cf28e2e123";
    private final static String TASK_ID = "asdf735d-5aa5-228b-ad36-69cf28e2e123";
    private final static String USER_ID = "58607299-b756-4f72-922d-07e3c9f1448d";
    private final static String DATE_START = "dateStart";
    private final static String DATE_END = "dateEnd";
    private final static String ORDER = "order";
    private final static String STATUS = "status";
    private final static String KEY_WORD = "TE";
    private TaskClient taskClient;
    private ProjectClient projectClient;


    @Before
    public void setUp() {
        projectClient = ProjectClient.client(URL_PROJECT);
        final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(PROJECT_ID);
        projectDTO.setUserId(USER_ID);
        projectDTO.setName("TEST");
        projectDTO.setDescription("TEST");
        assertNotNull(projectClient.merge(projectDTO));
        assertNotNull(projectClient.findOne(PROJECT_ID));
        taskClient = TaskClient.client(URL_TASK);
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(TASK_ID);
        taskDTO.setUserId(USER_ID);
        taskDTO.setProjectId(PROJECT_ID);
        taskDTO.setName("TEST");
        taskDTO.setDescription("TEST");
        assertNotNull(taskClient.merge(taskDTO));
        assertNotNull(taskClient.findOne(TASK_ID));
    }

    @Test
    public void findAllByUserId() {
        final List<TaskDTO> tasks = taskClient.findAllByUserId(USER_ID);
        assertNotNull(tasks);
        assertTrue(tasks.size() > 0);
    }

    @Test
    public void sortByUserId() {
        List<TaskDTO> tasks = taskClient.sortByUserId(USER_ID, DATE_START);
        assertNotNull(tasks);
        assertTrue(tasks.size() > 0);
        tasks = taskClient.sortByUserId(USER_ID, DATE_END);
        assertNotNull(tasks);
        assertTrue(tasks.size() > 0);
        tasks = taskClient.sortByUserId(USER_ID, STATUS);
        assertNotNull(tasks);
        assertTrue(tasks.size() > 0);
        tasks = taskClient.sortByUserId(USER_ID, ORDER);
        assertNotNull(tasks);
        assertTrue(tasks.size() > 0);
    }

    @Test
    public void findByPartOfNameOrDescription() {
        List<TaskDTO> tasks = taskClient.findByPartOfNameOrDescription(USER_ID, KEY_WORD);
        assertNotNull(tasks);
        assertTrue(tasks.size() > 0);
    }

    @Test
    public void findOneByUserId() {
        assertNotNull(taskClient.findOneByUserId(USER_ID, TASK_ID));
    }

    @Test
    public void findAllByProjectId() {
        List<TaskDTO> tasks = taskClient.findAllByProjectId(USER_ID, PROJECT_ID);
        assertNotNull(tasks);
        assertTrue(tasks.size() > 0);
    }

    @Test
    public void findAll() {
        List<TaskDTO> tasks = taskClient.findAll();
        assertNotNull(tasks);
        assertTrue(tasks.size() > 0);
    }

    @After
    public void tearDown() {
        //cascade
        assertNotNull(projectClient.removeById(PROJECT_ID));
    }

}
