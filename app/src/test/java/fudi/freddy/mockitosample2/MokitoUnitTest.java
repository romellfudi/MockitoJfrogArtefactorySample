package fudi.freddy.mockitosample2;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MokitoUnitTest {

    @Mock
    private MainContract.View mMainView; // Contract

    @InjectMocks
    private MainPresenter mMainPresenter = new MainPresenter(mMainView); // presenter

    @Mock
    private RepoService repoService; // Contract

    @Captor
    private ArgumentCaptor<RepoService.UserServiceCallback<User>> userServiceCallbackCaptor; // object or Callback
    @Captor
    private ArgumentCaptor<User> captorUser; // object or Callback
    @Captor
    private ArgumentCaptor<String> captorString; // object or Callback
    @Captor
    private ArgumentCaptor<Integer> captorInteger; // object or Callback

    @Before
    public void inject() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCallbacks() {
        String dataForUser = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur";
        mMainPresenter.saveInput(dataForUser);
        verify(repoService).save(captorUser.capture());
        final User user = captorUser.getValue();
        assertThat(user.getData(), is(dataForUser));
        verify(mMainView,times(1)).clearEdittext();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((RepoService.UserServiceCallback<User>) invocation.getArguments()[1]).load(user);
                return null;
            }
        }).when(repoService).loadUser(anyInt(), any(RepoService.UserServiceCallback.class));
        mMainPresenter.loadLastText();
        verify(mMainView).viewLoadData(captorString.capture());
        assertThat(user.getData(), is(captorString.getValue()));

        final List<User> usuarios = Arrays.asList(user,user,user,user,user);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((RepoService.UserServiceCallback<List<User>>) invocation.getArguments()[0]).load(usuarios);
                return null;
            }
        }).when(repoService).loadUsers(any(RepoService.UserServiceCallback.class));
        mMainPresenter.pullAllData();
        verify(mMainView, times(2)).viewLoadData(captorString.capture());
        assertThat("5", is(captorString.getValue()));

    }

    @Test
    public void whenNotUseMockReturn() {
        List mockList = Mockito.mock(ArrayList.class);

        mockList.add("one");
        verify(mockList).add("one");
        assertEquals(0, mockList.size());

        when(mockList.size()).thenReturn(100);
        assertEquals(100, mockList.size());
    }
}