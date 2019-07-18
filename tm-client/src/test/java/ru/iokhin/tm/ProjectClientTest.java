package ru.iokhin.tm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.iokhin.tm.api.ProjectClient;
import ru.iokhin.tm.api.UserClient;
import ru.iokhin.tm.model.ProjectDTO;

import java.util.List;

import static junit.framework.TestCase.*;

public class ProjectClientTest {

    private final static String URL = "http://localhost:8080/api/project";
    private final static String PROJECT_ID = "678f735d-5aa5-408b-ad36-69cf28e2e123";
    private final static String USER_ID = "58607299-b756-4f72-922d-07e3c9f1448d";
    private final static String DATE_START = "dateStart";
    private final static String DATE_END = "dateEnd";
    private final static String ORDER = "order";
    private final static String STATUS = "status";
    private final static String KEY_WORD = "TE";
    private ProjectClient projectClient;

    @Before
    public void setUp() {
        projectClient = ProjectClient.client(URL);
        final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(PROJECT_ID);
        projectDTO.setUserId(USER_ID);
        projectDTO.setName("TEST");
        projectDTO.setDescription("TEST");
        assertNotNull(projectClient.merge(projectDTO));
        assertNotNull(projectClient.findOne(PROJECT_ID));
    }

    @Test
    public void findAllByUserId() {
        final List<ProjectDTO> projects = projectClient.findAllByUserId(USER_ID);
        assertNotNull(projects);
        assertTrue(projects.size() > 0);
    }

    @Test
    public void sortByUserId() {
        List<ProjectDTO> projects = projectClient.sortByUserId(USER_ID, DATE_START);
        assertNotNull(projects);
        assertTrue(projects.size() > 0);
        projects = projectClient.sortByUserId(USER_ID, DATE_END);
        assertNotNull(projects);
        assertTrue(projects.size() > 0);
        projects = projectClient.sortByUserId(USER_ID, STATUS);
        assertNotNull(projects);
        assertTrue(projects.size() > 0);
        projects = projectClient.sortByUserId(USER_ID, ORDER);
        assertNotNull(projects);
        assertTrue(projects.size() > 0);
    }

    @Test
    public void findByPartOfNameOrDescription() {
        List<ProjectDTO> projects = projectClient.findByPartOfNameOrDescription(USER_ID, KEY_WORD);
        assertNotNull(projects);
        assertTrue(projects.size() > 0);
    }

    @Test
    public void findOneByUserId() {
        assertNotNull(projectClient.findOneByUserId(USER_ID, PROJECT_ID));
    }

    @Test
    public void findAll() {
        List<ProjectDTO> projects = projectClient.findAll();
        assertNotNull(projects);
        assertTrue(projects.size() > 0);
    }

    @After
    public void tearDown() {
        assertNotNull(projectClient.removeById(PROJECT_ID));
    }
}
