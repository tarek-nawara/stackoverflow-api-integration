package edu.stackoverflow.guice;

import com.google.inject.name.Names;

public class StackoverflowTestingModule extends StackoverflowModule {
    /** {@inheritDoc} */
    @Override
    public void bindParametersFile() {
        bindConstant().annotatedWith(Names.named("parameters-file-path")).to("api-test.json");
    }
}
