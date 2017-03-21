/*
 * Copyright 2014 Click Travel Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.clicktravel.cheddar.metrics.intercom.random.data;

import static com.clicktravel.common.random.Randoms.randomEmailAddress;
import static com.clicktravel.common.random.Randoms.randomId;
import static com.clicktravel.common.random.Randoms.randomIntInRange;
import static com.clicktravel.common.random.Randoms.randomString;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clicktravel.cheddar.metrics.MetricOrganisation;
import com.clicktravel.cheddar.metrics.MetricUser;

import io.intercom.api.Company;
import io.intercom.api.CompanyCollection;
import io.intercom.api.CustomAttribute;
import io.intercom.api.User;

@SuppressWarnings("rawtypes")
public class RandomDataGenerator {

    public static MetricOrganisation randomMetricOrganisation() {
        return new MetricOrganisation(randomId(), randomString());
    }

    public static MetricUser randomMetricUser() {
        final int numberOfOrganisations = randomIntInRange(1, 10);
        final List<String> organisationIds = new ArrayList<>();
        for (int i = 0; i < numberOfOrganisations; i++) {
            organisationIds.add(randomId());
        }
        return new MetricUser(randomId(), organisationIds, randomString(), randomEmailAddress(),
                randomMetricCustomAttributes());
    }

    public static User randomIntercomUser() {
        final User user = new User();
        user.setId(randomId());
        user.setCompanyCollection(randomCompanyCollection());
        user.setName(randomString());
        user.setEmail(randomEmailAddress());
        user.setCustomAttributes(randomCustomAttributes());
        return user;
    }

    public static CompanyCollection randomCompanyCollection() {
        final List<Company> companies = new ArrayList<>();
        final CompanyCollection companyCollection = mock(CompanyCollection.class);
        final int numberOfCompanies = randomIntInRange(1, 10);

        for (int i = 0; i < numberOfCompanies; i++) {
            final Company mockCompany = mock(Company.class);
            final String companyId = randomId();
            when(mockCompany.getCompanyID()).thenReturn(companyId);

            companies.add(mockCompany);
        }

        when(companyCollection.getPage()).thenReturn(companies);
        return companyCollection;
    }

    public static Map<String, Object> randomMetricCustomAttributes() {
        final Map<String, Object> customAttributes = new HashMap<>();
        final int numberOfCustomAttributes = randomIntInRange(1, 10);
        for (int i = 0; i < numberOfCustomAttributes; i++) {
            final String key = randomString();
            final Object mockCustomAttribute = mock(CustomAttribute.class);
            customAttributes.put(key, mockCustomAttribute);
        }

        return customAttributes;
    }

    public static Map<String, CustomAttribute> randomCustomAttributes() {
        final Map<String, CustomAttribute> customAttributes = new HashMap<>();

        final int numberOfCustomAttributes = randomIntInRange(1, 10);

        for (int i = 0; i < numberOfCustomAttributes; i++) {
            final String key = randomString();
            final CustomAttribute mockCustomAttribute = mock(CustomAttribute.class);

            customAttributes.put(key, mockCustomAttribute);
        }

        return customAttributes;
    }

}
