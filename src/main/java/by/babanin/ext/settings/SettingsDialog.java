package by.babanin.ext.settings;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JDialog;

import by.babanin.ext.component.util.GUIUtils;
import by.babanin.ext.message.TranslateCode;
import by.babanin.ext.message.Translator;
import by.babanin.ext.preference.DimensionPreference;
import by.babanin.ext.preference.PointPreference;
import by.babanin.ext.preference.PreferenceAware;
import by.babanin.ext.preference.PreferencesGroup;
import by.babanin.ext.preference.until.PreferenceUtils;

public class SettingsDialog extends JDialog implements PreferenceAware<PreferencesGroup> {

    public static final String SETTINGS_DIALOG_CLOSING_ACTION_KEY = "closePrioritiesDialog";
    private static final String SIZE_KEY = "settingsDialogSize";
    private static final String LOCATION_KEY = "settingsDialogLocation";

    public SettingsDialog(Frame owner, SettingsPanel settingsPanel) {
        super(owner, true);
        init(settingsPanel);
    }

    public SettingsDialog(Dialog owner, SettingsPanel settingsPanel) {
        super(owner, true);
        init(settingsPanel);
    }

    public SettingsDialog(Window owner, SettingsPanel settingsPanel) {
        super(owner, ModalityType.APPLICATION_MODAL);
        init(settingsPanel);
    }

    public void init(SettingsPanel settingsPanel) {
        setName("settingsDialog");
        setTitle(Translator.toLocale(TranslateCode.MAIN_MENU_SETTINGS));
        settingsPanel.setCancelAction(GUIUtils.addCloseActionOnEscape(this, SETTINGS_DIALOG_CLOSING_ACTION_KEY));
        rootPane.setDefaultButton(settingsPanel.getDefaultButton());
        setContentPane(settingsPanel);
        GUIUtils.addPreferenceSupport(this);
    }

    @Override
    public SettingsPanel getContentPane() {
        return (SettingsPanel) super.getContentPane();
    }

    @Override
    public void apply(PreferencesGroup preferencesGroup) {
        preferencesGroup.getOpt(getContentPane().getKey())
                .ifPresent(preference -> PreferenceUtils.applyLater(getContentPane(), (PreferencesGroup) preference));
        preferencesGroup.getOpt(SIZE_KEY)
                .ifPresent(preference -> {
                    Dimension size = ((DimensionPreference) preference).getDimension();
                    setMinimumSize(size);
                    setSize(size);
                });
        preferencesGroup.getOpt(LOCATION_KEY)
                .ifPresentOrElse(preference -> setLocation(((PointPreference) preference).getPoint()),
                        () -> setLocationRelativeTo(getOwner()));
    }

    @Override
    public PreferencesGroup createCurrentPreference() {
        DimensionPreference dimensionPreference = new DimensionPreference();
        dimensionPreference.setDimension(getSize());
        PointPreference pointPreference = new PointPreference();
        pointPreference.setPoint(getLocation());
        PreferencesGroup preferencesGroup = new PreferencesGroup();
        preferencesGroup.put(SIZE_KEY, dimensionPreference);
        preferencesGroup.put(LOCATION_KEY, pointPreference);
        preferencesGroup.put(getContentPane().getKey(), getContentPane().createCurrentPreference());
        return preferencesGroup;
    }

    @Override
    public PreferencesGroup createDefaultPreference() {
        DimensionPreference dimensionPreference = new DimensionPreference();
        dimensionPreference.setDimension(GUIUtils.getHalfFrameSize());
        PreferencesGroup preferencesGroup = new PreferencesGroup();
        preferencesGroup.put(SIZE_KEY, dimensionPreference);
        preferencesGroup.put(getContentPane().getKey(), getContentPane().createDefaultPreference());
        return preferencesGroup;
    }

    @Override
    public String getKey() {
        return getName();
    }
}
