package dev.jamilleni.grademanager;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {
    List<Grade> gradeList = new ArrayList<>(Arrays.asList(
        new Grade("Jamille", "EECS 1019", "Discrete Math for Computer Science", "A+"),
        new Grade("Jamille", "EECS 2030", "Advanced Object Oriented Programming", "A+"))
        );

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        int index = getGradeIndex(id);
        model.addAttribute("grade", index == Constants.NOTFOUND ? new Grade() : gradeList.get(index));
        return "form";
    }

    @PostMapping("/submit")
    public String submitForm(Grade grade) {
        int index = getGradeIndex(grade.getId());

        if (index == Constants.NOTFOUND) {
            gradeList.add(grade);
        } else {
            gradeList.set(index, grade);
        }
        return "redirect:/grades";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", gradeList);

        return "grades";
    }

    public int getGradeIndex(String id) {

        for (int i = 0; i < gradeList.size(); i++) {
            if (gradeList.get(i).getId().equals(id)){
                return i;
            }
        }

        return Constants.NOTFOUND;
    }
}
