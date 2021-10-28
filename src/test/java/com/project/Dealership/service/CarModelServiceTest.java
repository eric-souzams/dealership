package com.project.Dealership.service;

import com.project.Dealership.dto.request.CarModelRequest;
import com.project.Dealership.dto.response.CarModelResponse;
import com.project.Dealership.exceptions.CarModelNotFoundException;
import com.project.Dealership.mocks.CarModel.CarModelMock;
import com.project.Dealership.mocks.CarModel.CarModelRequestMock;
import com.project.Dealership.mocks.CarModel.CarModelResponseMock;
import com.project.Dealership.model.entity.CarModel;
import com.project.Dealership.repository.CarModelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CarModelServiceTest {

    @InjectMocks
    private CarModelService carModelService;

    @Mock
    private CarModelRepository carModelRepository;

    @Mock
    private CarModelResponse carModelResponse;

    @Mock
    private CarModelRequest carModelRequest;

    @Mock
    private Page<CarModel> page;

    @Test
    public void itShouldBeCreateAnNewCarModel() {
        //given
        CarModel expectedCarModel = new CarModelMock().newMock();
        CarModelResponse expectedResponse = new CarModelResponseMock().newMock();
        CarModelRequest expectedRequest = new CarModelRequestMock().newMock();

        //when
        when(carModelRequest.toEntity(expectedRequest)).thenReturn(expectedCarModel);
        when(carModelRepository.save(expectedCarModel)).thenReturn(expectedCarModel);
        when(carModelResponse.toResponse(expectedCarModel)).thenReturn(expectedResponse);

        //then
        CarModelResponse result = carModelService.save(expectedRequest);

        assertEquals(result.getId(), expectedCarModel.getId());
        assertNotNull(result.getId());
    }

    @Test
    public void itShouldBeFindAnCarModelById() {
        //given
        Long informedId = 1L;
        CarModel expectedCarModel = new CarModelMock().newMock();
        CarModelResponse expectedResponse = new CarModelResponseMock().newMock();

        //when
        when(carModelRepository.findById(informedId)).thenReturn(Optional.of(expectedCarModel));
        when(carModelResponse.toResponse(expectedCarModel)).thenReturn(expectedResponse);

        //then
        CarModelResponse result = carModelService.find(informedId);

        assertEquals(result.getId(), expectedCarModel.getId());
        assertEquals(result.getBrand(), expectedCarModel.getBrand());
        assertNotNull(result.getId());
    }

    @Test
    public void itShouldBeThrowAnExceptionWhenCarModelNotFound() {
        //given
        Long informedId = 1L;

        //when
        when(carModelRepository.findById(informedId)).thenReturn(Optional.empty());

        //then
        assertThrows(CarModelNotFoundException.class, () -> carModelService.find(informedId));
    }

    @Test
    public void itShouldBeFindAllCarModelsPaginated() {
        //given
        CarModel expectedCarModel = new CarModelMock().newMock();
        CarModelResponse expectedResponse = new CarModelResponseMock().newMock();

        Page<CarModel> expectedCarModelPage = new PageImpl<>(List.of(expectedCarModel));
        Page<CarModelResponse> expectedResponsePage = new PageImpl<>(List.of(expectedResponse));

        //when
        when(carModelRepository.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(expectedCarModelPage);
        when(carModelResponse.toResponse(expectedCarModel)).thenReturn(expectedResponse);
        when(page.map(carModelResponse::toResponse)).thenReturn(expectedResponsePage);

        //then
        Page<CarModelResponse> foundCarModelList = carModelService.findAll(PageRequest.of(1, 1));

        assertEquals(foundCarModelList.toList().get(0).getId(), expectedCarModel.getId());
        assertEquals(foundCarModelList.toList().get(0).getBrand(), expectedCarModel.getBrand());
    }
}
