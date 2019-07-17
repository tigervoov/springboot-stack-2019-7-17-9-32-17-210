package com.tw.apistackbase;


import com.tw.apistackbase.entity.CaseInfo;
import com.tw.apistackbase.repository.CaseInfoRepository;
import com.tw.apistackbase.repository.CaseRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.tw.apistackbase.entity.Case;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class caseTest {

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private CaseInfoRepository caseInfoRepository;



    @Test
    public void findCaseById() {
        //given
        Case case1 =new Case("盗窃",new Date().getTime());
        Case case2 =new Case("拐卖",new Date().getTime());
        List<Case> cases= Arrays.asList(case1,case2);
        caseRepository.saveAll(cases);
        caseRepository.flush();

        //when+then
        Assertions.assertEquals("拐卖",caseRepository.findById(case2.getId()).get().getCaseName());
    }
    @Test
    public void findAllCases(){
        //given
        Case case1 =new Case("盗窃",new Date().getTime());
        Case case2 =new Case("拐卖",new Date().getTime());
        List<Case> cases= Arrays.asList(case1,case2);
        caseRepository.saveAll(cases);
        caseRepository.flush();

        //When
        List<Case> casesList=caseRepository.findAll();

        //then
        Assertions.assertEquals(2,casesList.size());
    }
    @Test
    public void findByCasesOrderByTimeDesc(){
        //given
        Case case1 =new Case("盗窃",new Date().getTime());
        Case case2 =new Case("拐卖",new Date().getTime()+60);
        Case case3 =new Case("打架",new Date().getTime()+120);
        List<Case> cases= Arrays.asList(case1,case2,case3);
        caseRepository.saveAll(cases);
        caseRepository.flush();

        //When
        List<Case> casesList=caseRepository.findByOrderByTimeDesc();

        //then
        Assertions.assertEquals("打架",casesList.get(0).getCaseName());
    }
    @Test
    public void findCaseByCaseName(){
        //given
        Case case1 =new Case("盗窃",new Date().getTime());
        Case case2 =new Case("拐卖",new Date().getTime()+60);
        Case case3 =new Case("打架",new Date().getTime()+120);
        List<Case> cases= Arrays.asList(case1,case2,case3);
        caseRepository.saveAll(cases);
        caseRepository.flush();

        //When
        List<Case> casesList=caseRepository.findAllByName("盗窃");

        //then
        Assertions.assertEquals("盗窃",casesList.get(0).getCaseName());
    }
    @Test
    public void deleteCaseById(){
        //given
        Case case1 =new Case("盗窃",new Date().getTime());
        Case case2 =new Case("拐卖",new Date().getTime()+60);
        Case case3 =new Case("打架",new Date().getTime()+120);
        List<Case> cases= Arrays.asList(case1,case2,case3);
        caseRepository.saveAll(cases);
        caseRepository.flush();

        //When
        caseRepository.deleteById(case1.getId());

        //then
        Assertions.assertNull(caseRepository.findById(case1.getId()).orElse(null));
    }
    @Test
    public void findCaseInfoById(){
        //given
        CaseInfo caseInfo1=new CaseInfo("aaabbb","bbbaaa");
        CaseInfo caseInfo2=new CaseInfo("cccddd","dddccc");
        List<CaseInfo> caseInfoList=Arrays.asList(caseInfo1,caseInfo2);
        caseInfoRepository.saveAll(caseInfoList);
        caseInfoRepository.flush();

        //When+then
        Assertions.assertEquals("cccddd",caseInfoRepository.findById(caseInfo2.getId()).get().getSubjectiveInfo());
    }

}
