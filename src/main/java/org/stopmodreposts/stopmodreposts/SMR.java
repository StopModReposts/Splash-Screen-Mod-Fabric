package org.stopmodreposts.stopmodreposts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.fabricmc.loader.api.metadata.CustomValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class SMR implements PreLaunchEntrypoint {
    public static final Gson GSON = new GsonBuilder().create();
    public static boolean isModMenuLoaded = false;
    public static Settings settings;
    private static Path settingsFile;

    @Override
    public void onPreLaunch() {
        isModMenuLoaded = FabricLoader.getInstance().isModLoaded("modmenu");
        settingsFile = FabricLoader.getInstance().getConfigDir().resolve("./stopmodreposts.json");
        try {
            if (settingsFile.toFile().exists()) {
                String settingsString = String.join("", Files.readAllLines(settingsFile));
                settings = GSON.fromJson(settingsString, Settings.class);
            } else {
                hostElection();
            }
        } catch (IOException e) {
            System.out.println("Unable to load StopModReposts config. Please, fix this issue!");
            e.printStackTrace();
        }
    }

    private static void hostElection() {
        AtomicInteger voteOnToast = new AtomicInteger();
        AtomicInteger voteOnButton = new AtomicInteger();
        AtomicInteger participantsNumber = new AtomicInteger();
        FabricLoader.getInstance().getAllMods().stream()
            .map(ModContainer::getMetadata)
            .forEach(modMetadata -> {
                boolean participatesInElection = false;
                if (modMetadata.containsCustomValue("stopmodreposts:showToast")
                    && modMetadata.getCustomValue("stopmodreposts:showToast").getType() == CustomValue.CvType.BOOLEAN) {
                    voteOnToast.addAndGet(modMetadata.getCustomValue("stopmodreposts:showToast").getAsBoolean() ? 1 : -1);
                    participatesInElection = true;
                }
                if (modMetadata.containsCustomValue("stopmodreposts:showButton")
                    && modMetadata.getCustomValue("stopmodreposts:showButton").getType() == CustomValue.CvType.BOOLEAN) {
                    voteOnButton.addAndGet(modMetadata.getCustomValue("stopmodreposts:showButton").getAsBoolean() ? 1 : -1);
                    participatesInElection = true;
                }
                if (participatesInElection) {
                    participantsNumber.addAndGet(1);
                }
            });
        // Fallback election results
        if (participantsNumber.get() == 0) {
            voteOnButton.set(1);
            voteOnToast.set(1);
        }
        Settings settings = new Settings();
        settings.showToast = voteOnToast.get() >= 0;
        settings.showButton = voteOnButton.get() >= 0;
        saveSettings(settings);
    }

    static void saveSettings(Settings newSettings) {
        settings = newSettings;
        try {
            String settingsString = GSON.toJson(newSettings, Settings.class);
            Files.write(settingsFile, Arrays.asList(settingsString.split("\\r?\\n")));
        } catch (IOException e) {
            System.out.println("Unable to save StopModReposts config. Please, fix this issue!");
            e.printStackTrace();
        }

    }

    public static final class Settings {
        public boolean showToast = true;
        public boolean showButton = true;
    }
}
