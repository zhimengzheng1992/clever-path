package bcit.cst;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Zhimeng Zheng
 * @version 2025-08-21
 */
public class LogTest {

    // 创建 logger
    private static final Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testLogging() {
        logger.trace("This is a TRACE log - fine-grained debug info");
        logger.debug("This is a DEBUG log - useful for debugging");
        logger.info("This is an INFO log - general application flow");
        logger.warn("This is a WARN log - something unexpected, but not critical");
        logger.error("This is an ERROR log - something went wrong");
    }
}
