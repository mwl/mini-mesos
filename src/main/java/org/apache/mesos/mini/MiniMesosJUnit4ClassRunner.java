package org.apache.mesos.mini;

import org.apache.mesos.mini.mesos.MesosClusterConfig;
import org.junit.rules.TestRule;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import java.util.List;

public class MiniMesosJUnit4ClassRunner extends BlockJUnit4ClassRunner {
    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @param klass
     * @throws InitializationError if the test class is malformed.
     */
    public MiniMesosJUnit4ClassRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected List<TestRule> classRules() {
        final List<TestRule> testClassAnnotatedRules = super.classRules();
        testClassAnnotatedRules.add(new MesosCluster(MesosClusterConfig.builder().numberOfSlaves(3).slaveResources("ports(*):[9200-9200,9300-9300]", "ports(*):[9201-9201,9301-9301]", "ports(*):[9202-9202,9302-9302]").build()));
        return testClassAnnotatedRules;
    }
}
