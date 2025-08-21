package bcit.cst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CleverpathManagementApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(CleverpathManagementApplication.class, args);
    }

}
