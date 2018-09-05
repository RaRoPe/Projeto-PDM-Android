package br.iesb.calc.calculadora_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import static java.lang.Boolean.FALSE;

public class MainActivity extends AppCompatActivity {

    String acumulador = "", acumulador2 = "";
    String operacao = "";
    String formatada = ""; //String que será usada para a formatação de saída para os textView
    String uso_em_ponto = "";

    TextView t1;
    TextView t2;
    TextView t3;
    double result = 0;

    int checkClick = 0; //Inteiro que será utilizado para a verificação de dois operadores sendo apertados sequencialmente
    int checkClickPonto = 0; //Inteiro igual ao anterior, mas para a operação de ponto.

    /*Condição que colocará o ponto no valor.
    * O valor falso representa que irá adicionar um ponto no double.
    * Será falso até que se faça uma operação.
    * Quer dizer que não pode colocar dois pontos sequenciais em uma operação.
    * O valor true quer dizer que já tem um ponto no valor.
    * */
    boolean condicaoPonto = false;

    String toast_message_zero = "ERRO!!\nDivisão por 0.";
    String toast_message_ponto = "ERRO!!\nNão aperte o ponto mais de uma vez.";
    int duration = Toast.LENGTH_SHORT;

    /*
    private class XA implements View.OnClickListener{
        @Override
        public void onClick(View view){

            Toast.makeText(MainActivity.this, "Teste", Toast.LENGTH_LONG).show();

        }
    }*/

    public static String format(double d){
        if (d == (int) d){
            return String.format("%d",(int)d);
        }
        else
            return String.format("%s",d);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Indica qual é o xml que vai representar o xml da tela do layout

        t1 = findViewById(R.id.txtEntrada);
        t2 = findViewById(R.id.txtOperador);
        t3 = findViewById(R.id.txtResultado);

        t1.setText("0");
        t2.setText("");
        t3.setText("");

        Button b1 = findViewById(R.id.buttonUM);
        Button b2 = findViewById(R.id.buttonDOIS);
        Button b3 = findViewById(R.id.buttonTRES);
        Button b4 = findViewById(R.id.buttonQUATRO);
        Button b5 = findViewById(R.id.buttonCINCO);
        Button b6 = findViewById(R.id.buttonSEIS);
        Button b7 = findViewById(R.id.buttonSETE);
        Button b8 = findViewById(R.id.buttonOITO);
        Button b9 = findViewById(R.id.buttonNOVE);
        Button b0 = findViewById(R.id.buttonZERO);
        Button bMAIS = findViewById(R.id.buttonMAIS);
        Button bMENOS = findViewById(R.id.buttonMENOS);
        Button bBARRA = findViewById(R.id.buttonBARRA);
        Button bERASE = findViewById(R.id.buttonERASE);
        Button bSQRT = findViewById(R.id.buttonSQRT);
        Button bVEZES = findViewById(R.id.buttonVEZES);
        Button bIGUAL = findViewById(R.id.buttonIGUAL);
        Button bPONTO = findViewById(R.id.buttonPONTO);

        /*View.OnClickListener x = new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view) {

            }
        }
        b1.setOnClickListener(x);*/

        //Toda vez que for fazer um clique, seja em imagem, texto, botão, faz-se o código abaixo
        b1.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){
                bufferNumeros("1");
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){
                bufferNumeros("2");
            }
        });
        b3.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){
                bufferNumeros("3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){
                bufferNumeros("4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){ bufferNumeros("5");
            }
        });
        b6.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){
                bufferNumeros("6");
            }
        });
        b7.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){
                bufferNumeros("7");
            }
        });
        b8.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){
                bufferNumeros("8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){
                bufferNumeros("9");
            }
        });
        b0.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){
                bufferNumeros("0");
            }
        });

        bMAIS.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){
                t2.setText("+");
                bufferOperador("MAIS");
                setOperacao();
            }
        });

        bMENOS.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){
                t2.setText("-");
                bufferOperador("MENOS");
                setOperacao();
            }
        });

        bVEZES.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){
                t2.setText("x");
                bufferOperador("VEZES");
                setOperacao();
            }
        });

        bBARRA.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){
                t2.setText("/");
                bufferOperador("BARRA");
                setOperacao();
            }
        });

        bSQRT.setOnClickListener(new View.OnClickListener(){ //Criação de uma classe anônima
            public void onClick(View view){
                t2.setText("√");
                bufferOperador("SQRT");
                setOperacao();
            }
        });

        bERASE.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                bufferOperador("ERASE");
                setErase();
            }
        });

        bPONTO.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                bufferOperadorPonto();
                setOperacaoPonto();
            }
        });

        bIGUAL.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //t2.setText("=");
                bufferOperador("IGUAL");
                resultado();
                operacao = "";
            }
        });
    }

    public void bufferNumeros(String valor){
        if (condicaoPonto == false) { //Caso o usuário não tenha apertado ponto
            acumulador += valor;
            t1.setText(acumulador);
            checkClick = 0;
        } else { //Caso o usuário tenha apertado ponto
            acumulador += valor;
            t1.setText(uso_em_ponto+acumulador); //Mostra o valor do acumulador com ponto
            checkClick = 0;
        }
    }

    public void bufferOperador(String valor) {
        checkClick += 1;
        acumulador2 = valor;
        t1.setText("");
        if (condicaoPonto == true) {
            setOperacaoPonto();
        }
        checkClickPonto = 0;
    }

    public void bufferOperadorPonto(){
        checkClickPonto += 1;
        t1.setText("");
    }

    public void setOperacao() {
        if (condicaoPonto == true) {
            operacao = acumulador2;
            condicaoPonto = false;
            return;
        }
        if (checkClick == 1) {
            result = Double.parseDouble(acumulador);
            formatada = format(result);
            t1.setText(formatada);
            operacao = acumulador2;
            acumulador = "";
        }
        if (checkClick >= 2) {
            operacao = acumulador2;
            formatada = format(result);
            t1.setText(formatada);
        }
    }

    public void setOperacaoPonto(){
        if (checkClickPonto == 1){
            if (condicaoPonto == false){
                uso_em_ponto = acumulador; //Armazena os primeiros dígitos antes do ponto
                uso_em_ponto += ".";
                condicaoPonto = true; //O usuário apertou ponto
                t1.setText(acumulador + "."); //Mostra o valor de acumulador na tela
                acumulador = "";
                return;
            } else {
                if (acumulador2 != "IGUAL") {
                    uso_em_ponto += acumulador;
                    result = Double.parseDouble(uso_em_ponto);
                    formatada = format(result);
                    t1.setText(formatada); //OK
                    acumulador = "";
                    uso_em_ponto = "";
                } else{
                    uso_em_ponto += acumulador;
                    acumulador = uso_em_ponto;
                    formatada = format(result);
                    t1.setText(formatada);
                    uso_em_ponto = "";
                }
            }
        }
        else {
            if (checkClickPonto == 2) {
                Toast toast = Toast.makeText(getApplicationContext(), toast_message_ponto, duration);
                toast.show();
                t1.setText(uso_em_ponto+acumulador);
                checkClickPonto = 1;
            }
        }
    }

    public void setErase(){
        result = 0;
        operacao = "";
        acumulador = "";
        acumulador2 = "";
        uso_em_ponto = "";
        condicaoPonto = false;
        checkClickPonto = 0;
        t1.setText("0");
        t2.setText("");
        t3.setText("");
    }

    public void resultado(){
        if (operacao.equals("MAIS")){
            if ((acumulador.equals("")) == FALSE){
                t1.setText(format(result)+"\n"+acumulador);
                result += Double.parseDouble(acumulador);
                formatada = format(result);
                t3.setText(formatada);
            } else {
                formatada = format(result);
                t3.setText(formatada);
            }
        }
        else if (operacao.equals("MENOS")){
            t1.setText(format(result)+"\n"+acumulador);
            result -= Double.parseDouble(acumulador);
            formatada = format(result);
            t3.setText(formatada);
        }
        else if (operacao.equals("VEZES")){
            t1.setText(format(result)+"\n"+acumulador);
            result *= Double.parseDouble(acumulador);
            formatada = format(result);
            t3.setText(formatada);
        }
        else if (operacao.equals("BARRA")){
            if (uso_em_ponto != "") {
                if (Double.parseDouble(uso_em_ponto) != 0) {
                    t1.setText(format(result)+"\n"+acumulador);
                    result /= Double.parseDouble(uso_em_ponto);
                    formatada = format(result);
                    t3.setText(formatada);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), toast_message_zero, duration);
                    toast.show();
                    setErase();
                }
            } else {
                if (Double.parseDouble(acumulador) != 0) {
                    t1.setText(format(result)+"\n"+acumulador);
                    result /= Double.parseDouble(acumulador);
                    formatada = format(result);
                    t3.setText(formatada);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), toast_message_zero, duration);
                    toast.show();
                    setErase();
                }
            }
        }
        else if (operacao.equals("SQRT")){
            t1.setText(format(result));
            result = Math.sqrt(result);
            formatada = format(result);
            t3.setText(formatada);
        }
        acumulador = "";
        acumulador2 = "";
        uso_em_ponto = "";
    }
}