package com.ef.parser;

import com.ef.parser.domain.ArgumentsTest;
import com.ef.parser.service.CommentLogServiceTest;
import com.ef.parser.service.LogServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CommentLogServiceTest.class,
        LogServiceTest.class,
        ArgumentsTest.class
})
public class ApplicationTests {
    @Test
    public void contextLoads() {
    }
}
