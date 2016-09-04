package gppmds.wikilegis.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import gppmds.wikilegis.exception.UserException;
import gppmds.wikilegis.model.User;

import gppmds.wikilegis.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterUserFragment extends Fragment implements View.OnClickListener{

    private static final String STRING_EMPTY ="";

    private EditText firstNameField = null;
    private EditText lastNameField = null;
    private EditText emailField = null;
    private EditText passwordField = null;
    private EditText passwordConfirmationField = null;

    private String firstName = STRING_EMPTY;
    private String lastName = STRING_EMPTY;
    private String email = STRING_EMPTY;
    private String password = STRING_EMPTY;
    private String passwordConfirmation = STRING_EMPTY;

    public RegisterUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_user, container, false);

        Button register = (Button) view.findViewById(R.id.registerButton);

        register.setOnClickListener(this);
        this.settingEditText(view);

        return view;
    }

    private void settingEditText(View view){
        this.firstNameField = (EditText) view.findViewById(R.id.firstNameField);
        this.lastNameField = (EditText) view.findViewById(R.id.lastNameField);
        this.emailField = (EditText) view.findViewById(R.id.emailField);
        this.passwordField = (EditText) view.findViewById(R.id.passwordField);
        this.passwordConfirmationField = (EditText) view.findViewById(R.id.passwordConfirmationField);
    }

    private void settingTextTyped(){
        this.firstName = firstNameField.getText().toString();
        this.lastName = lastNameField.getText().toString();
        this.email = emailField.getText().toString();
        this.password = passwordField.getText().toString();
        this.passwordConfirmation = passwordConfirmationField.getText().toString();
    }

    @Override
    public void onClick(View view){

        this.settingTextTyped();

        this.validateUserInformation();
    }

    private void setMessageError(EditText editText, String message){
        assert editText != null;
        assert message != null;

        editText.requestFocus();
        editText.setError(message);
    }

    private void validateUserInformation(){
        try{
            User user = new User(firstName, lastName, email, password);

            String SUCCESSFUL_REGISTRATION_MESSAGE = "Cadastro efetuado com sucesso!";
            Toast.makeText(getActivity().getBaseContext(),
                    SUCCESSFUL_REGISTRATION_MESSAGE, Toast.LENGTH_LONG).show();

        }catch(UserException e){
            String exceptionMessage = e.getMessage();

            switch(exceptionMessage){
                case User.FIRST_NAME_CANT_BE_EMPTY:
                    setMessageError(firstNameField, exceptionMessage);
                    break;
                case User.FIRST_NAME_CANT_BE_HIGHER_THAN_30:
                    setMessageError(firstNameField, exceptionMessage);
                    break;
                case User.LAST_NAME_CANT_BE_EMPTY:
                    setMessageError(lastNameField, exceptionMessage);
                    break;
                case User.LAST_NAME_CANT_BE_HIGHER_THAN_30:
                    setMessageError(lastNameField, exceptionMessage);
                    break;
                case User.EMAIL_CANT_BE_EMPTY_EMAIL:
                    setMessageError(emailField, exceptionMessage);
                    break;
                case User.EMAIL_CANT_BE_HIGHER_THAN_150:
                    setMessageError(emailField, exceptionMessage);
                    break;
                case User.INVALID_EMAIL:
                    setMessageError(emailField, exceptionMessage);
                    break;
                case User.PASSWORD_CANT_BE_EMPTY:
                    setMessageError(passwordField, exceptionMessage);
                    break;
                case User.PASSWORD_CANT_BE_LESS_THAN_6:
                    setMessageError(passwordField, exceptionMessage);
                    break;
                case User.PASSWORD_CANT_BE_HIGHER_THAN_10:
                    setMessageError(passwordField, exceptionMessage);
                    break;
                /*
                case User.PASSWORD_ARE_NOT_EQUALS:
                    setMessageError(passwordField, exceptionMessage);
                    break;
                */
                default:
                    //nothing to do
                    break;
            }
        }
    }
}
