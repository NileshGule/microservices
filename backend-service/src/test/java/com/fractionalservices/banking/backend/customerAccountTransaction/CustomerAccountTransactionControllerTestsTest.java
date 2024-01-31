package com.fractionalservices.banking.backend.controller;

import com.fractionalservices.banking.backend.model.MyModel;
import com.fractionalservices.banking.backend.service.MyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MyControllerTest {

    @Mock
    private MyService myService;

    @InjectMocks
    private MyController myController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllModels() {
        // Arrange
        MyModel model1 = new MyModel(1, "Model 1");
        MyModel model2 = new MyModel(2, "Model 2");
        List<MyModel> expectedModels = Arrays.asList(model1, model2);
        when(myService.getAllModels()).thenReturn(expectedModels);

        // Act
        ResponseEntity<List<MyModel>> response = myController.getAllModels();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedModels, response.getBody());
        verify(myService, times(1)).getAllModels();
    }

    @Test
    public void testGetModelById() {
        // Arrange
        int id = 1;
        MyModel expectedModel = new MyModel(id, "Model 1");
        when(myService.getModelById(id)).thenReturn(expectedModel);

        // Act
        ResponseEntity<MyModel> response = myController.getModelById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedModel, response.getBody());
        verify(myService, times(1)).getModelById(id);
    }

    @Test
    public void testCreateModel() {
        // Arrange
        MyModel model = new MyModel(1, "Model 1");
        when(myService.createModel(model)).thenReturn(model);

        // Act
        ResponseEntity<MyModel> response = myController.createModel(model);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(model, response.getBody());
        verify(myService, times(1)).createModel(model);
    }

    @Test
    public void testUpdateModel() {
        // Arrange
        int id = 1;
        MyModel model = new MyModel(id, "Updated Model");
        when(myService.updateModel(id, model)).thenReturn(model);

        // Act
        ResponseEntity<MyModel> response = myController.updateModel(id, model);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(model, response.getBody());
        verify(myService, times(1)).updateModel(id, model);
    }

    @Test
    public void testDeleteModel() {
        // Arrange
        int id = 1;

        // Act
        ResponseEntity<Void> response = myController.deleteModel(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(myService, times(1)).deleteModel(id);
    }
}