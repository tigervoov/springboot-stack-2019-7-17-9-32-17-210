package com.tw.apistackbase;


import com.tw.apistackbase.entity.CaseInfo;
import com.tw.apistackbase.entity.Court;
import com.tw.apistackbase.entity.Prosecutor;
import com.tw.apistackbase.repository.CaseInfoRepository;
import com.tw.apistackbase.repository.CaseRepository;
import com.tw.apistackbase.repository.CourtRepository;
import com.tw.apistackbase.repository.ProsecutorRepository;
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

    @Autowired
    private CourtRepository courtRepository;

    @Autowired
    private ProsecutorRepository prosecutorRepository;



    @Test
    public void findCaseById() {
        //given
        Case case1 =new Case("盗窃",new Date().getTime(),new CaseInfo("aaabbb","bbbaaa"));
        Case case2 =new Case("拐卖",new Date().getTime()+60,new CaseInfo("cccddd","dddccc"));
        Case case3 =new Case("打架",new Date().getTime()+120,new CaseInfo("eeefff","fffeee"));
        List<Case> cases= Arrays.asList(case1,case2);
        caseRepository.saveAll(cases);
        caseRepository.flush();

        //when+then
        Assertions.assertEquals("拐卖",caseRepository.findById(case2.getId()).get().getCaseName());
    }
    @Test
    public void findAllCases(){
        //given
        Case case1 =new Case("盗窃",new Date().getTime(),new CaseInfo("aaabbb","bbbaaa"));
        Case case2 =new Case("拐卖",new Date().getTime()+60,new CaseInfo("cccddd","dddccc"));
        Case case3 =new Case("打架",new Date().getTime()+120,new CaseInfo("eeefff","fffeee"));
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
        Case case1 =new Case("盗窃",new Date().getTime(),new CaseInfo("aaabbb","bbbaaa"));
        Case case2 =new Case("拐卖",new Date().getTime()+60,new CaseInfo("cccddd","dddccc"));
        Case case3 =new Case("打架",new Date().getTime()+120,new CaseInfo("eeefff","fffeee"));
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
        Case case1 =new Case("盗窃",new Date().getTime(),new CaseInfo("aaabbb","bbbaaa"));
        Case case2 =new Case("拐卖",new Date().getTime()+60,new CaseInfo("cccddd","dddccc"));
        Case case3 =new Case("打架",new Date().getTime()+120,new CaseInfo("eeefff","fffeee"));
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
        Case case1 =new Case("盗窃",new Date().getTime(),new CaseInfo("aaabbb","bbbaaa"));
        Case case2 =new Case("拐卖",new Date().getTime()+60,new CaseInfo("cccddd","dddccc"));
        Case case3 =new Case("打架",new Date().getTime()+120,new CaseInfo("eeefff","fffeee"));
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

    @Test
    public void findCaseAndCaseInfoByCaseId(){
        //given
        Case case1 =new Case("盗窃",new Date().getTime(),new CaseInfo("aaabbb","bbbaaa"));
        Case case2 =new Case("拐卖",new Date().getTime()+60,new CaseInfo("cccddd","dddccc"));
        Case case3 =new Case("打架",new Date().getTime()+120,new CaseInfo("eeefff","fffeee"));
        List<Case> caseList= Arrays.asList(case1,case2,case3);
        caseRepository.saveAll(caseList);
        caseRepository.flush();

        //When+then
        Assertions.assertEquals("eeefff",caseRepository.findById(case3.getId()).get().getCaseInfo().getSubjectiveInfo());
    }
    @Test
    public void findCaseAndCourtByCaseId(){
        //given
        Court court1=new Court("court1");
        Court court2=new Court("court2");
        List<Court> courtList= Arrays.asList(court1,court2);
        courtRepository.saveAll(courtList);
        courtRepository.flush();

        //When+then
        Assertions.assertEquals("court2",courtRepository.findById(court2.getId()).get().getCourtName());
    }
    @Test
    public void findCourtByCourtId(){
        //given
        Case case1 =new Case("盗窃",new Date().getTime(),new CaseInfo("aaabbb","bbbaaa"));
        case1.setCourt(new Court("court1"));
        Case case2 =new Case("拐卖",new Date().getTime()+60,new CaseInfo("cccddd","dddccc"));
        case2.setCourt(new Court("court2"));
        List<Case> caseList= Arrays.asList(case1,case2);
        caseRepository.saveAll(caseList);
        caseRepository.flush();

        //When+then
        Assertions.assertEquals("court1",courtRepository.findById(case1.getId()).get().getCourtName());
    }
    @Test
    public void findProsecutorById(){
        //given
        Prosecutor prosecutor1=new Prosecutor("mike");
        Prosecutor prosecutor2=new Prosecutor("John");
        List<Prosecutor> caseList= Arrays.asList(prosecutor1,prosecutor2);
        prosecutorRepository.saveAll(caseList);
        prosecutorRepository.flush();

        //When+then
        Assertions.assertEquals("mike",prosecutorRepository.findById(prosecutor1.getId()).get().getName());
    }

    @Test
    public void findProsecutorsByCourt(){
        //given
        Prosecutor prosecutor1=new Prosecutor("mike");
        Prosecutor prosecutor2=new Prosecutor("John");
        Court court=new Court("court1");
        List<Prosecutor> prosecutorList= Arrays.asList(prosecutor1,prosecutor2);
        court.setProsecutor(prosecutorList);
        courtRepository.save(court);
        courtRepository.flush();

        //When+then
        Assertions.assertEquals("mike",courtRepository.findById(court.getId()).get().getProsecutor().get(0).getName());
    }




}
