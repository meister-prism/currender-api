package meister.prism.currender.api

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import meister.prism.currender.api.infrastructure.repository.MemoRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.FileInputStream

@SpringBootApplication
class CurrenderApiApplication

fun main(args: Array<String>) {
	runApplication<CurrenderApiApplication>(*args)
	execStartUp()
}

fun execStartUp(): Unit {
	val serviceAccount = FileInputStream("./credential.json")
	val options = FirebaseOptions.Builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.setDatabaseUrl("https://currender-db.firebaseio.com/")
			.build()
	FirebaseApp.initializeApp(options);
	MemoRepository().getImgAll()
}

