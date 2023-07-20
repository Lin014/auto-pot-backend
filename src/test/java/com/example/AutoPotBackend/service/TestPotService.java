package com.example.AutoPotBackend.service;

import com.example.AutoPotBackend.dao.PotDao;
import com.example.AutoPotBackend.entity.Pot;
import com.example.AutoPotBackend.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TestPotService {
    @InjectMocks
    private PotService potService;
    @Mock
    private PotDao potDao;

    User testUser = new User(1);
    Pot p1 = new Pot(1, "Test1", testUser);
    Pot p2 = new Pot(2, "Test2", testUser);
    Pot p5 = new Pot(5, "Test5", testUser);
    Pot pMax = new Pot(Integer.MAX_VALUE, "TestMax", testUser);
    Pot pMaxD1 = new Pot(Integer.MAX_VALUE-1, "TestMaxD1", testUser);

    // 1-Max

    // Equivalence
    // <0 - -3, 1-Max - 5, >Max - Max+3

    // pot_id = -3 (invalid)
    @Test
    public void getPotByIdTestD3() {
        Mockito.when(potDao.getPot(-3)).thenReturn(null);
        Pot responsePot = potService.getPot(-3);

        assertThat(responsePot).isEqualTo(null);
        System.out.println("Get Pot By Id -3 Test Pass");
    }

    // pot_id = 5 (valid)
    @Test
    public void getPotByIdTest5() {
        Mockito.when(potDao.getPot(5)).thenReturn(p5);
        Pot responsePot = potService.getPot(5);

        assertThat(responsePot.getPot_id()).isEqualTo(5);
        System.out.println("Get Pot By Id 5 Test Pass");
    }

    // pot_id = Max+3 (invalid)
    @Test
    public void getPotByIdTestA3() {
        Mockito.when(potDao.getPot(Integer.MAX_VALUE+3)).thenReturn(null);
        Pot responsePot = potService.getPot(Integer.MAX_VALUE+3);

        assertThat(responsePot).isEqualTo(null);
        System.out.println("Get Pot By Id Max+3 Test Pass");
    }

    // Boundary
    // 0, 1, 2, Max-1, Max, Max+1

    // pot_id = 0 (invalid)
    @Test
    public void getPotByIdTest0() {
        Mockito.when(potDao.getPot(0)).thenReturn(null);
        Pot responsePot = potService.getPot(0);

        assertThat(responsePot).isEqualTo(null);
        System.out.println("Get Pot By Id 0 Test Pass");
    }

    // pot_id = 1 (valid)
    @Test
    public void getPotByIdTest1() {
        Mockito.when(potDao.getPot(1)).thenReturn(p1);
        Pot responsePot = potService.getPot(1);

        assertThat(responsePot.getPot_id()).isEqualTo(1);
        System.out.println("Get Pot By Id 1 Test Pass");
    }

    // pot_id = 2 (valid)
    @Test
    public void getPotByIdTest2() {
        Mockito.when(potDao.getPot(2)).thenReturn(p2);
        Pot responsePot = potService.getPot(2);

        assertThat(responsePot.getPot_id()).isEqualTo(2);
        System.out.println("Get Pot By Id 2 Test Pass");
    }

    // pot_id = Max-1 (valid)
    @Test
    public void getPotByIdTestMaxD1() {
        Mockito.when(potDao.getPot(Integer.MAX_VALUE-1)).thenReturn(pMaxD1);
        Pot responsePot = potService.getPot(Integer.MAX_VALUE-1);

        assertThat(responsePot.getPot_id()).isEqualTo(Integer.MAX_VALUE-1);
        System.out.println("Get Pot By Id Max-1 Test Pass");
    }

    // pot_id = Max (valid)
    @Test
    public void getPotByIdTestMax() {
        Mockito.when(potDao.getPot(Integer.MAX_VALUE)).thenReturn(pMax);
        Pot responsePot = potService.getPot(Integer.MAX_VALUE);

        assertThat(responsePot.getPot_id()).isEqualTo(Integer.MAX_VALUE);
        System.out.println("Get Pot By Id Max Test Pass");
    }

    // pot_id = Max+1 (invalid)
    @Test
    public void getPotByIdTestMaxA1() {
        Mockito.when(potDao.getPot(Integer.MAX_VALUE+1)).thenReturn(null);
        Pot responsePot = potService.getPot(Integer.MAX_VALUE+1);

        assertThat(responsePot).isEqualTo(null);
        System.out.println("Get Pot By Id Max+1 Test Pass");
    }


}
