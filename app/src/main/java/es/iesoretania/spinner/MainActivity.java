package es.iesoretania.spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import es.iesoretania.spinner.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityMainBinding.inflate(getLayoutInflater( ) );
        setContentView( binding.getRoot( ) );

        Spinner spinner = findViewById( R.id.spinner );

        String[ ] operaciones = { "Suma", "Resta", "Multiplicación", "División" };
        ArrayAdapter <String> adapter = new ArrayAdapter<>( this, R.layout.spinner_item, operaciones );
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter( adapter );

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String opcionSeleccionada = (String) spinner.getItemAtPosition( i );

                if( binding.editTextNumber1.getText( ).toString( ).isEmpty( ) || binding.editTextNumber2.getText( ).toString( ).isEmpty( ) ) {
                    return;
                }

                int valor1 = Integer.parseInt( binding.editTextNumber1.getText( ).toString( ) );
                int valor2 = Integer.parseInt( binding.editTextNumber2.getText( ).toString( ) );
                int resultado = 0;

                switch( opcionSeleccionada )
                {
                    case "Suma": resultado = valor1 + valor2; break;
                    case "Resta": resultado = valor1 - valor2; break;
                    case "Multiplicación": resultado = valor1 * valor2; break;
                    case "División": resultado = valor1 / valor2; break;
                }

                binding.textViewResultado.setText( String.valueOf( resultado ) );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


}