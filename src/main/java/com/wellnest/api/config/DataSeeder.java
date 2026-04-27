package com.wellnest.api.config;

import com.wellnest.api.model.AppUser;
import com.wellnest.api.model.ResourceType;
import com.wellnest.api.model.Role;
import com.wellnest.api.model.UsageMetric;
import com.wellnest.api.model.WellnessProgram;
import com.wellnest.api.model.WellnessResource;
import com.wellnest.api.repository.UsageMetricRepository;
import com.wellnest.api.repository.UserRepository;
import com.wellnest.api.repository.WellnessProgramRepository;
import com.wellnest.api.repository.WellnessResourceRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
  private final UserRepository userRepository;
  private final WellnessResourceRepository resourceRepository;
  private final WellnessProgramRepository programRepository;
  private final UsageMetricRepository metricRepository;
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public DataSeeder(
      UserRepository userRepository,
      WellnessResourceRepository resourceRepository,
      WellnessProgramRepository programRepository,
      UsageMetricRepository metricRepository) {
    this.userRepository = userRepository;
    this.resourceRepository = resourceRepository;
    this.programRepository = programRepository;
    this.metricRepository = metricRepository;
  }

  @Override
  public void run(String... args) {
    seedUsers();
    seedResources();
    seedPrograms();
    seedMetrics();
  }

  private void seedUsers() {
    createUser("WellNest Admin", "admin@wellnest.com", "Bharath@321", Role.ADMIN);
    createUser("Demo Student", "student@university.edu", "Bharath@321", Role.STUDENT);
  }

  private void createUser(String name, String email, String password, Role role) {
    if (userRepository.existsByEmail(email)) {
      return;
    }
    AppUser user = new AppUser();
    user.setName(name);
    user.setEmail(email);
    user.setPasswordHash(passwordEncoder.encode(password));
    user.setRole(role);
    userRepository.save(user);
  }

  private void seedResources() {
    if (resourceRepository.count() > 0) {
      return;
    }

    List<WellnessResource> resources = List.of(
        resource("Stress Management Toolkit", "Evidence-based steps for exam pressure, deadlines, and burnout.", ResourceType.MENTAL_HEALTH, "Stress Relief", "15 min", "https://images.unsplash.com/photo-1506126613408-eca07ce68773?w=900", "Counselling Team"),
        resource("Guided Mindfulness Reset", "A short meditation routine students can use between classes.", ResourceType.MENTAL_HEALTH, "Meditation", "20 min", "https://images.unsplash.com/photo-1545389336-cf090694435e?w=900", "Dr. Emily Watson"),
        resource("Sleep Hygiene for Students", "Practical habits for better rest and sharper focus.", ResourceType.MENTAL_HEALTH, "Sleep", "12 min", "https://images.unsplash.com/photo-1541781774459-bb2af2f05b55?w=900", "Wellness Office"),
        resource("Balanced Student Diet", "Affordable nutrition guidance for busy academic schedules.", ResourceType.NUTRITION, "Balanced", "8 min", "https://images.unsplash.com/photo-1490645935967-10de6ba17061?w=900", "Nutrition Team"),
        resource("High Protein Recovery Guide", "Meal ideas that support training, recovery, and energy.", ResourceType.NUTRITION, "High Protein", "10 min", "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=900", "Sarah Thompson")
    );
    resourceRepository.saveAll(resources);
  }

  private WellnessResource resource(String title, String description, ResourceType type, String category, String duration, String imageUrl, String author) {
    WellnessResource resource = new WellnessResource();
    resource.setTitle(title);
    resource.setDescription(description);
    resource.setType(type);
    resource.setCategory(category);
    resource.setDuration(duration);
    resource.setImageUrl(imageUrl);
    resource.setAuthor(author);
    resource.setContent(description + " Includes student-friendly action steps, reflection prompts, and support guidance.");
    return resource;
  }

  private void seedPrograms() {
    if (programRepository.count() > 0) {
      return;
    }

    List<WellnessProgram> programs = List.of(
        program("Beginner Yoga Flow", "Gentle mobility and breathing practice for study breaks.", "Sarah Johnson", "Beginner", "Yoga", "30 min", 234, ResourceType.FITNESS, "https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?w=900"),
        program("HIIT Cardio Blast", "Fast interval training for energy and stamina.", "Mike Chen", "Intermediate", "Cardio", "25 min", 189, ResourceType.FITNESS, "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?w=900"),
        program("Strength Training 101", "Learn safe lifting patterns and foundational strength.", "Alex Rivera", "Beginner", "Strength", "40 min", 312, ResourceType.FITNESS, "https://images.unsplash.com/photo-1534438327276-14e5300c3a48?w=900"),
        program("Mindfulness Workshop", "Weekly mental reset with reflection and guided breathing.", "Dr. Emily Watson", "All Levels", "Mental Health", "45 min", 156, ResourceType.MENTAL_HEALTH, "https://images.unsplash.com/photo-1499209974431-9dddcece7f88?w=900")
    );
    programRepository.saveAll(programs);
  }

  private WellnessProgram program(String title, String description, String instructor, String level, String category, String duration, int enrolled, ResourceType type, String imageUrl) {
    WellnessProgram program = new WellnessProgram();
    program.setTitle(title);
    program.setDescription(description);
    program.setInstructor(instructor);
    program.setLevel(level);
    program.setCategory(category);
    program.setDuration(duration);
    program.setEnrolled(enrolled);
    program.setType(type);
    program.setImageUrl(imageUrl);
    return program;
  }

  private void seedMetrics() {
    if (metricRepository.count() > 0) {
      return;
    }
    for (int index = 5; index >= 0; index--) {
      UsageMetric metric = new UsageMetric();
      metric.setMetricDate(LocalDate.now().minusMonths(index).withDayOfMonth(1));
      metric.setActiveUsers(4200 + ((6 - index) * 900));
      metric.setSessions(2400 + ((6 - index) * 650));
      metric.setResourceViews(9000 + ((6 - index) * 1700));
      metric.setProgramEnrollments(300 + ((6 - index) * 70));
      metricRepository.save(metric);
    }
  }
}
