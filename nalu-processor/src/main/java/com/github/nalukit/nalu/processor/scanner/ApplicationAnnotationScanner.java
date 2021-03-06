/*
 * Copyright (c) 2018 - 2019 - Frank Hossfeld
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  use this file except in compliance with the License. You may obtain a copy of
 *  the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations under
 *  the License.
 */

package com.github.nalukit.nalu.processor.scanner;

import com.github.nalukit.nalu.client.application.annotation.Application;
import com.github.nalukit.nalu.processor.ProcessorConstants;
import com.github.nalukit.nalu.processor.ProcessorException;
import com.github.nalukit.nalu.processor.ProcessorUtils;
import com.github.nalukit.nalu.processor.model.MetaModel;
import com.github.nalukit.nalu.processor.model.intern.ClassNameModel;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;

import static java.util.Objects.isNull;

public class ApplicationAnnotationScanner {

  private final static String APPLICATION_PROPERTIES = "application.properties";

  private ProcessorUtils processorUtils;

  private ProcessingEnvironment processingEnvironment;

  private MetaModel metaModel;

  private Element applicationElement;

  @SuppressWarnings("unused")
  private ApplicationAnnotationScanner(Builder builder) {
    super();
    this.processingEnvironment = builder.processingEnvironment;
    this.metaModel = builder.metaModel;
    this.applicationElement = builder.applicationElement;
    setUp();
  }

  public static Builder builder() {
    return new Builder();
  }

  private void setUp() {
    this.processorUtils = ProcessorUtils.builder()
                                        .processingEnvironment(this.processingEnvironment)
                                        .build();
  }

  public void scan()
      throws ProcessorException {
    Application applicationAnnotation = applicationElement.getAnnotation(Application.class);
    if (!isNull(applicationAnnotation)) {
      TypeElement applicationLoaderTypeElement = this.getApplicationLoaderTypeElement(applicationAnnotation);
      //      TypeElement shellTypeElement = this.getShellTypeElement(applicationAnnotation);
      TypeElement contextTypeElement = this.getContextTypeElement(applicationAnnotation);
      metaModel.setGenerateToPackage(this.processorUtils.getPackageAsString(applicationElement));
      metaModel.setApplication(new ClassNameModel(applicationElement.toString()));
      metaModel.setLoader(new ClassNameModel(isNull(applicationLoaderTypeElement) ? "" : applicationLoaderTypeElement.toString()));
      metaModel.setContext(new ClassNameModel(contextTypeElement.toString()));
      metaModel.setStartRoute(applicationAnnotation.startRoute());
      metaModel.setRouteError(applicationAnnotation.routeError());
      metaModel.setUsingHash(applicationAnnotation.useHash());
      metaModel.setUsingColonForParametersInUrl(applicationAnnotation.useColonForParametersInUrl());
    }
  }

  private TypeElement getApplicationLoaderTypeElement(Application applicationAnnotation) {
    try {
      applicationAnnotation.loader();
    } catch (MirroredTypeException exception) {
      return (TypeElement) this.processingEnvironment.getTypeUtils()
                                                     .asElement(exception.getTypeMirror());
    }
    return null;
  }

  private TypeElement getContextTypeElement(Application applicationAnnotation) {
    try {
      applicationAnnotation.context();
    } catch (MirroredTypeException exception) {
      return (TypeElement) this.processingEnvironment.getTypeUtils()
                                                     .asElement(exception.getTypeMirror());
    }
    return null;
  }

  private String createRelativeFileName() {
    return ProcessorConstants.META_INF + "/" + ProcessorConstants.NALU_REACT_FOLDER_NAME + "/" + ApplicationAnnotationScanner.APPLICATION_PROPERTIES;
  }

  public static class Builder {

    ProcessingEnvironment processingEnvironment;

    MetaModel metaModel;

    Element applicationElement;

    public Builder processingEnvironment(ProcessingEnvironment processingEnvironment) {
      this.processingEnvironment = processingEnvironment;
      return this;
    }

    public Builder metaModel(MetaModel metaModel) {
      this.metaModel = metaModel;
      return this;
    }

    public Builder applicationElement(Element applicationElement) {
      this.applicationElement = applicationElement;
      return this;
    }

    public ApplicationAnnotationScanner build() {
      return new ApplicationAnnotationScanner(this);
    }

  }

}
