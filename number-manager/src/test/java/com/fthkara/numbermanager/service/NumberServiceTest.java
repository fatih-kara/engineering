package com.fthkara.numbermanager.service;

import com.fthkara.numbermanager.model.NumberDocument;
import com.fthkara.numbermanager.repository.NumberRepository;
import com.fthkara.numbermanager.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class NumberServiceTest {

    @MockBean
    private NumberRepository numberRepository;

    @Autowired
    private NumberService numberService;

    @TestConfiguration
    static class NumberServiceConfiguration {

        @Bean
        public NumberService numberService() {
            return new NumberService();
        }
    }
    @Test
    public void save() {
        given(numberRepository.save(Mockito.any(NumberDocument.class))).willReturn(new NumberDocument());

        NumberDocument numberDocument = new NumberDocument(25, DateUtil.getDate());

        assertThat(numberService.save(numberDocument)).isEqualTo(null);
    }

    @Test
    public void findByNumber() {
        int number = 26;
        NumberDocument numberModel = new NumberDocument(number, DateUtil.getDate());

        given(numberRepository.findByNumber(anyInt())).willReturn(numberModel);

        NumberDocument result = numberService.findByNumber(number);

        assertThat(result.getNumber()).isEqualTo(number);
    }

    @Test
    public void findMaximumNumber() {

        NumberDocument numberModel = new NumberDocument(25, DateUtil.getDate());

        given(numberRepository.findTopByOrderByNumberDesc()).willReturn(numberModel);

        int result = numberService.findMaximumNumber();

        assertThat(result).isEqualTo(numberModel.getNumber());
    }

    @Test
    public void findMinimumNumber() {

        NumberDocument numberModel = new NumberDocument(10, DateUtil.getDate());

        given(numberRepository.findTopByOrderByNumberAsc()).willReturn(numberModel);

        int result = numberService.findMinimumNumber();

        assertThat(result).isEqualTo(numberModel.getNumber());
    }

    @Test
    public void findAllByNumberOrderByNumberAsc() {
        NumberDocument numberModel = new NumberDocument(25, DateUtil.getDate());
        NumberDocument numberModel2 = new NumberDocument(12, DateUtil.getDate());
        List<NumberDocument> numbers = Arrays.asList(numberModel, numberModel2);

        given(numberRepository.findAllByOrderByNumberAsc()).willReturn(numbers);

        assertThat(numberService.findAllByNumber(""))
                .hasSize(2)
                .contains(numberModel, numberModel2);
    }

    @Test
    public void findAllByNumberOrderByNumberDesc() {
        NumberDocument numberModel = new NumberDocument(25, DateUtil.getDate());
        NumberDocument numberModel2 = new NumberDocument(12, DateUtil.getDate());
        List<NumberDocument> numbers = Arrays.asList(numberModel, numberModel2);

        given(numberRepository.findAllByOrderByNumberDesc()).willReturn(numbers);

        assertThat(numberService.findAllByNumber("Desc"))
                .hasSize(2)
                .contains(numberModel, numberModel2);
    }
}