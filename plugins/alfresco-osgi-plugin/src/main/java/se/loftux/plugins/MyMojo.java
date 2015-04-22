package se.loftux.plugins;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import gradle.BundleService;
import gradle.RestClient;
import gradle.configuration.Authentication;
import gradle.configuration.Endpoint;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

/**
 * Goal which touches a timestamp file.
 *
 * @deprecated Don't use!
 */
@Mojo( name = "installBundle", defaultPhase = LifecyclePhase.PROCESS_SOURCES )
public class MyMojo
    extends AbstractMojo
{
//    /**
//     * Location of the file.
//     */
//    @Parameter( defaultValue = "${project.build.directory}", property = "outputDir", required = true )
//    private File outputDirectory;


    @Parameter( defaultValue = "${project.build.directory}/../${project.artifactId}/lib/${project.artifactId}.jar", property = "outputJar", required = true )
    private File outputJar;

    @Parameter( defaultValue = "admin", property = "userName", required = true )
    private String userName;

    @Parameter( defaultValue = "admin", property = "password", required = true )
    private String password;

    @Parameter( defaultValue = "http", property = "endpoint.protocol", required = true )
    private String protocol;

    @Parameter( defaultValue = "localhost", property = "endpoint.host", required = true )
    private String host;

    @Parameter( defaultValue = "8080", property = "endpoint.port", required = true )
    private String port;

    @Parameter( defaultValue = "/alfresco/service", property = "endpoint.serviceUrl", required = true )
    private String serviceUrl;

    public void execute() throws MojoExecutionException
    {
        getLog().info("Loftux OSGi Upload Plugin initialized.");
        if(outputJar.exists()){
            getLog().info("Preparing to upload new Bundle to Alfresco repository.");
            BundleService b = new BundleService();
            RestClient client = new RestClient();
            Authentication authentication = new Authentication();

            authentication.setUsername(userName);
            authentication.setPassword(password);

            client.setAuthentication(authentication);
            b.setClient(client);

            Endpoint endpoint = new Endpoint();
            endpoint.setProtocol(protocol);
            endpoint.setHost(host);
            endpoint.setPort(port);
            endpoint.setServiceUrl(serviceUrl);

            client.setEndpoint(endpoint);

            b.installBundle(outputJar);
            getLog().info("Uploading OSGi bundle completed.");
        }else{
            throw new MojoExecutionException("Output JAR file not found. Skipping Deployment." );
        }
    }
}


//        File f = outputDirectory;
//
//        if ( !f.exists() )
//        {
//            f.mkdirs();
//        }
//
//        File touch = new File( f, "touch.txt" );
//
//        FileWriter w = null;
//        try
//        {
//            w = new FileWriter( touch );
//
//            w.write( "touch.txt" );
//        }
//        catch ( IOException e )
//        {
//            throw new MojoExecutionException( "Error creating file " + touch, e );
//        }
//        finally
//        {
//            if ( w != null )
//            {
//                try
//                {
//                    w.close();
//                }
//                catch ( IOException e )
//                {
//                    // ignore
//                }
//            }
//        }
