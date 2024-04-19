import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RestController
public class MyController {
    
    @GetMapping(value = "/x/{id}")
    public ResponseEntity<Provider> getProvider(@PathVariable String id) {
        return new ResponseEntity<>("12", HttpStatus.OK);
        // try {
        //     // Provider provider = baloot.getProviderById(id);
        // } catch (NotExistentProvider e) {
        //     return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        // }
    }

    
}
