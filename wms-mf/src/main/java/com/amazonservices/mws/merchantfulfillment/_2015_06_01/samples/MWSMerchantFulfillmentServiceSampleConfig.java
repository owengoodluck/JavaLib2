/*******************************************************************************
 * Copyright 2009-2015 Amazon Services. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 *
 * You may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations under the License.
 *******************************************************************************
 * MWS Merchant Fulfillment Service
 * API Version: 2015-06-01
 * Library Version: 2015-06-01
 * Generated: Thu Oct 01 12:20:18 PDT 2015
 */
package com.amazonservices.mws.merchantfulfillment._2015_06_01.samples;

import com.amazonaws.mws.config.Owen;
import com.amazonservices.mws.merchantfulfillment._2015_06_01.MWSEndpoint;
import com.amazonservices.mws.merchantfulfillment._2015_06_01.MWSMerchantFulfillmentServiceAsyncClient;
import com.amazonservices.mws.merchantfulfillment._2015_06_01.MWSMerchantFulfillmentServiceClient;
import com.amazonservices.mws.merchantfulfillment._2015_06_01.MWSMerchantFulfillmentServiceConfig;

/**
 * Configuration for MWSMerchantFulfillmentService samples.
 */
public class MWSMerchantFulfillmentServiceSampleConfig {

    /** Developer AWS access key. */
    private static final String accessKey = Owen.accessKeyId;

    /** Developer AWS secret key. */
    private static final String secretKey = Owen.secretAccessKey;

    /** The client application name. */
    private static final String appName = Owen.appName;

    /** The client application version. */
    private static final String appVersion = Owen.appVersion;

    /**
     * The endpoint for region service and version.
     * ex: serviceURL = MWSEndpoint.NA_PROD.toString();
     */
    private static final String serviceURL =  MWSEndpoint.NA_PROD.toString();

    /** The client, lazy initialized. Async client is also a sync client. */
    private static MWSMerchantFulfillmentServiceAsyncClient client = null;

    /**
     * Get a client connection ready to use.
     *
     * @return A ready to use client connection.
     */
    public static MWSMerchantFulfillmentServiceClient getClient() {
        return getAsyncClient();
    }

    /**
     * Get an async client connection ready to use.
     *
     * @return A ready to use client connection.
     */
    public static synchronized MWSMerchantFulfillmentServiceAsyncClient getAsyncClient() {
        if (client==null) {
            MWSMerchantFulfillmentServiceConfig config = new MWSMerchantFulfillmentServiceConfig();
            config.setServiceURL(serviceURL);
            // Set other client connection configurations here.
            client = new MWSMerchantFulfillmentServiceAsyncClient(accessKey, secretKey, 
                    appName, appVersion, config, null);
        }
        return client;
    }

}
