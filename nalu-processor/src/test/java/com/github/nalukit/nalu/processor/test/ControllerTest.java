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

package com.github.nalukit.nalu.processor.test;

import com.github.nalukit.nalu.processor.NaluProcessor;
import com.google.testing.compile.Compilation;
import com.google.testing.compile.CompilationSubject;
import com.google.testing.compile.JavaFileObjects;
import org.junit.Test;

import javax.tools.JavaFileObject;
import java.util.ArrayList;

import static com.google.testing.compile.Compiler.javac;

@SuppressWarnings("serial")
public class ControllerTest {

  @Test
  public void testAcceptAnnotation01() {
    Compilation compilation = javac().withProcessors(new NaluProcessor())
                                     .compile(new ArrayList<JavaFileObject>() {
                                       {
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/MockShell.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation01/AcceptAnnotation01Controller.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation01/IComponent01.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation01/Component01.java"));
                                       }
                                     });
    CompilationSubject.assertThat(compilation)
                      .failed();
    CompilationSubject.assertThat(compilation)
                      .hadErrorContaining("Nalu-Processor: controller >>com.github.nalukit.nalu.processor.controller.acceptAnnotation01.AcceptAnnotation01Controller<< - @AcceptParameter with value >>parameter03<< is not represented in the route as parameter");
  }

  @Test
  public void testAcceptAnnotation02() {
    Compilation compilation = javac().withProcessors(new NaluProcessor())
                                     .compile(new ArrayList<JavaFileObject>() {
                                       {
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/MockShell.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation02/AcceptAnnotation02Controller.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation02/IComponent02.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation02/Component02.java"));
                                       }
                                     });
    CompilationSubject.assertThat(compilation)
                      .failed();
    CompilationSubject.assertThat(compilation)
                      .hadErrorContaining("annotation @com.github.nalukit.nalu.client.component.annotation.AcceptParameter is missing a default value for the element 'value'");
  }

  @Test
  public void testAcceptAnnotation03() {
    Compilation compilation = javac().withProcessors(new NaluProcessor())
                                     .compile(new ArrayList<JavaFileObject>() {
                                       {
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/MockShell.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation03/AcceptAnnotation03Controller.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation03/IComponent03.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation03/Component03.java"));
                                       }
                                     });
    CompilationSubject.assertThat(compilation)
                      .failed();
    CompilationSubject.assertThat(compilation)
                      .hadErrorContaining("Nalu-Processor: controller >>com.github.nalukit.nalu.processor.controller.acceptAnnotation03.AcceptAnnotation03Controller<< - @AcceptParameter on >>setParameter01(java.math.BigDecimal)<< parameter has the wrong type -> must be a String");
  }

  @Test
  public void testAcceptAnnotation04() {
    Compilation compilation = javac().withProcessors(new NaluProcessor())
                                     .compile(new ArrayList<JavaFileObject>() {
                                       {
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/MockShell.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation04/AcceptAnnotation04Controller.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation04/IComponent04.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation04/Component04.java"));
                                       }
                                     });
    CompilationSubject.assertThat(compilation)
                      .failed();
    CompilationSubject.assertThat(compilation)
                      .hadErrorContaining("Nalu-Processor: controller >>com.github.nalukit.nalu.processor.controller.acceptAnnotation04.AcceptAnnotation04Controller<< - @AcceptParameter on >>setParameter02(java.math.BigDecimal)<< parameter has the wrong type -> must be a String");
  }

  @Test
  public void testAcceptAnnotation05() {
    Compilation compilation = javac().withProcessors(new NaluProcessor())
                                     .compile(new ArrayList<JavaFileObject>() {
                                       {
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/MockShell.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation05/AcceptAnnotation05Controller.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation05/IComponent05.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/acceptAnnotation05/Component05.java"));
                                       }
                                     });
    CompilationSubject.assertThat(compilation)
                      .succeeded();
  }

  @Test
  public void testControllerAnnotationRouteDoesNotBeginWithSlash() {
    Compilation compilation = javac().withProcessors(new NaluProcessor())
                                     .compile(new ArrayList<JavaFileObject>() {
                                       {
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/routeDoesNotBeginWithSlash/RouteDoesNotBeginWithSlash.java"));
                                       }
                                     });
    CompilationSubject.assertThat(compilation)
                      .failed();
    CompilationSubject.assertThat(compilation)
                      .hadErrorContaining("Nalu-Processor: @Controller - route attribute muss begin with a '/'");
  }

  @Test
  public void testControllerAnnotationWithoutParameterOK() {
    Compilation compilation = javac().withProcessors(new NaluProcessor())
                                     .compile(new ArrayList<JavaFileObject>() {
                                       {
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerAnnotationWithoutParameterOK/ControllerAnnotationWithoutParameterOK.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/MockContext.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/MockShell.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/component01/Controller01.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/component01/IComponent01.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/component01/Component01.java"));
                                       }
                                     });
    CompilationSubject.assertThat(compilation)
                      .succeeded();
  }

  @Test
  public void testControllerAnnotationWithParameterNotOK() {
    Compilation compilation = javac().withProcessors(new NaluProcessor())
                                     .compile(new ArrayList<JavaFileObject>() {
                                       {
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerAnnotationWithParameterNotOK/ControllerAnnotationWithParameterNotOK.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/MockContext.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/MockShell.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerAnnotationWithParameterNotOK/ui/bad/BadController.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerAnnotationWithParameterNotOK/ui/bad/IBadComponent.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerAnnotationWithParameterNotOK/ui/bad/BadComponent.java"));
                                       }
                                     });
    CompilationSubject.assertThat(compilation)
                      .succeeded();
  }

  @Test
  public void testControllerAnnotationWithParameterOK() {
    Compilation compilation = javac().withProcessors(new NaluProcessor())
                                     .compile(new ArrayList<JavaFileObject>() {
                                       {
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerAnnotationWithParameterOK/ControllerAnnotationWithParameterOK.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/MockContext.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/MockShell.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/component02/Controller02.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/component02/IComponent02.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/component02/Component02.java"));
                                       }
                                     });
    CompilationSubject.assertThat(compilation)
                      .succeeded();
  }

  @Test
  public void testControllerWithIsComponentCreatorOK() {
    Compilation compilation = javac().withProcessors(new NaluProcessor())
                                     .compile(new ArrayList<JavaFileObject>() {
                                       {
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerWithIsComponentControllerOK/ControllerWithIsComponentControllerOK.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/MockContext.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/MockShell.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerWithIsComponentControllerOK/ui/content01/Content01Controller.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerWithIsComponentControllerOK/ui/content01/IContent01Component.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerWithIsComponentControllerOK/ui/content01/Content01Component.java"));
                                       }
                                     });
    CompilationSubject.assertThat(compilation)
                      .succeeded();
  }

  @Test
  public void testControllerWithIsComponentCreatorNotOK() {
    Compilation compilation = javac().withProcessors(new NaluProcessor())
                                     .compile(new ArrayList<JavaFileObject>() {
                                       {
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerWithIsComponentControllerNotOK/ControllerWithIsComponentControllerNotOK.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/MockContext.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/MockShell.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerWithIsComponentControllerNotOK/ui/content01/Content01Controller.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerWithIsComponentControllerNotOK/ui/content01/IContent01Component.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerWithIsComponentControllerNotOK/ui/content01/IContent02Component.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerWithIsComponentControllerNotOK/ui/content01/Content01Component.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/controllerWithIsComponentControllerNotOK/ui/content01/Content02Component.java"));
                                       }
                                     });
    CompilationSubject.assertThat(compilation)
                      .failed();
    CompilationSubject.assertThat(compilation)
                      .hadErrorContaining("Nalu-Processor: controller >>com.github.nalukit.nalu.processor.controller.controllerWithIsComponentControllerNotOK.ui.content01.Content01Controller<< is declared as IsComponentCreator, but the used reference of the component interface does not match with the one inside the controller");
  }

  @Test
  public void testGenerateWithoutIsComponentCreator() {
    Compilation compilation = javac().withProcessors(new NaluProcessor())
                                     .compile(new ArrayList<JavaFileObject>() {
                                       {
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/generateWithoutIsComponentCreator/GenerateWithoutIsComponentCreator.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/MockContext.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/MockShell.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/generateWithoutIsComponentCreator/ui/content01/Content01Controller.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/generateWithoutIsComponentCreator/ui/content01/IContent01Component.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/generateWithoutIsComponentCreator/ui/content01/Content01Component.java"));
                                       }
                                     });
    CompilationSubject.assertThat(compilation)
                      .succeeded();
    CompilationSubject.assertThat(compilation)
                      .generatedSourceFile("com/github/nalukit/nalu/processor/controller/generateWithoutIsComponentCreator/GenerateWithoutIsComponentCreatorImpl")
                      .hasSourceEquivalentTo(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/generateWithoutIsComponentCreator/GenerateWithoutIsComponentCreatorImpl.java"));
  }

  @Test
  public void testGenerateWithIsComponentCreator() {
    Compilation compilation = javac().withProcessors(new NaluProcessor())
                                     .compile(new ArrayList<JavaFileObject>() {
                                       {
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/generateWithIsComponentCreator/GenerateWithIsComponentCreator.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/MockContext.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/common/ui/MockShell.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/generateWithIsComponentCreator/ui/content01/Content01Controller.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/generateWithIsComponentCreator/ui/content01/IContent01Component.java"));
                                         add(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/generateWithIsComponentCreator/ui/content01/Content01Component.java"));
                                       }
                                     });
    CompilationSubject.assertThat(compilation)
                      .succeeded();
    CompilationSubject.assertThat(compilation)
                      .generatedSourceFile("com/github/nalukit/nalu/processor/controller/generateWithIsComponentCreator/GenerateWithIsComponentCreatorImpl")
                      .hasSourceEquivalentTo(JavaFileObjects.forResource("com/github/nalukit/nalu/processor/controller/generateWithIsComponentCreator/GenerateWithIsComponentCreatorImpl.java"));
  }
}
