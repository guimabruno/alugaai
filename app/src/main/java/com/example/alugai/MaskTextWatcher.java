package com.example.alugai;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * MaskTextWatcher é um TextWatcher que aplica uma máscara a um EditText.
 * Pode ser usado para formatar entradas como CPF, números de telefone ou códigos postais.
 */

public class MaskTextWatcher implements TextWatcher {
    private final EditText editText;
    private final String mask;
    private boolean isUpdating;
    private String old = "";

    /**
     * Construtor para MaskTextWatcher.
     *
     * @param editText O EditText ao qual a máscara será aplicada.
     * @param mask O padrão de máscara, usando '#' para representar caracteres numéricos.
     */

    public MaskTextWatcher(EditText editText, String mask) {
        this.editText = editText;
        this.mask = mask;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // No operation
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // No operation
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (isUpdating) {
            return;
        }

        String str = s.toString().replaceAll("[^\\d]", "");
        StringBuilder formatted = new StringBuilder();

        int i = 0;
        for (char m : mask.toCharArray()) {
            if (m != '#' && str.length() > old.length()) {
                formatted.append(m);
                continue;
            }

            try {
                formatted.append(str.charAt(i));
            } catch (Exception e) {
                break;
            }
            i++;
        }

        isUpdating = true;
        editText.setText(formatted.toString());
        editText.setSelection(formatted.length());
        isUpdating = false;
        old = str;
    }
}
