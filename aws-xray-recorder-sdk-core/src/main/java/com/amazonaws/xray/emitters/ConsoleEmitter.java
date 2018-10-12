package com.amazonaws.xray.emitters;

import com.amazonaws.xray.config.DaemonConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.xray.entities.Segment;
import com.amazonaws.xray.entities.Subsegment;

public class ConsoleEmitter extends Emitter {
    private static final Log logger = LogFactory.getLog(ConsoleEmitter.class);
    private DaemonConfiguration config;
    private byte[] sendBuffer = new byte[DAEMON_BUF_RECEIVE_SIZE];
    public ConsoleEmitter() {
        config = new DaemonConfiguration();
    }

    /**
     * {@inheritDoc}
     *
     * @see Emitter#sendSegment(Segment)
     */
    public boolean sendSegment(Segment segment) {
        if (logger.isDebugEnabled()) {
            logger.debug(segment.prettySerialize());
        }
        System.out.println("ConsoleEmitter sending segment: " + segment.prettySerialize());
        // return sendData((PROTOCOL_HEADER + PROTOCOL_DELIMITER + segment.serialize()).getBytes());
        return sendData((PROTOCOL_HEADER + PROTOCOL_DELIMITER + segment.serialize()).getBytes());
    }

    /**
     * {@inheritDoc}
     *
     * @see Emitter#sendSubsegment(Subsegment)
     */
    public boolean sendSubsegment(Subsegment subsegment) {
        if (logger.isDebugEnabled()) {
            logger.debug(subsegment.prettyStreamSerialize());
        }
        System.out.println("ConsoleEmitter sending segment: " + subsegment.prettyStreamSerialize());
        return sendData((PROTOCOL_HEADER + PROTOCOL_DELIMITER + subsegment.streamSerialize()).getBytes());
    }

    private boolean sendData(byte[] data) {
        return true;
    }
}
