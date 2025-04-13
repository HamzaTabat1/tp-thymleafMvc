package org.example.hopital.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import lombok.AllArgsConstructor;
import org.example.hopital.entities.Patient;
import org.example.hopital.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model, @RequestParam(name="page",defaultValue = "0") int p
                                    ,@RequestParam(name="size",defaultValue = "4") int s) {
        Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(p,s));
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("listPatients",pagePatients.getContent());
        model.addAttribute("currentpage",p);
        return "patients";
    }

}
