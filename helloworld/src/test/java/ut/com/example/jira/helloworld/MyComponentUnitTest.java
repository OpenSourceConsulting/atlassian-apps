package ut.com.example.jira.helloworld;

import org.junit.Test;
import com.example.jira.helloworld.api.MyPluginComponent;
import com.example.jira.helloworld.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}