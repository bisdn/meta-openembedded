SUMMARY = "File manager for GNOME"
SECTION = "x11/gnome"

LICENSE="GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

GNOMEBASEBUILDCLASS = "meson"

DEPENDS = " \
    appstream-glib-native \
    desktop-file-utils-native \
    glib-2.0 \
    gnome-autoar \
    gnome-desktop \
    gtk4 \
    libadwaita \
    libcloudproviders \
    libhandy \
    libportal \
    libxml2 \
    tracker \
"

inherit gnomebase gsettings gobject-introspection gtk-doc gettext features_check mime-xdg gtk-icon-cache

def gnome_verdir(v):
    return oe.utils.trim_version(v, 1)

SRC_URI[archive.sha256sum] = "360802a595e3edbad962c8cea353b62baa8be407513b5162c89c933ca5387aa9"

REQUIRED_DISTRO_FEATURES = "x11 opengl"

EXTRA_OEMESON += " \
    -Dtests=none \
"

PACKAGECONFIG = "extensions"
PACKAGECONFIG[extensions] = "-Dextensions=true,-Dextensions=false, gexiv2 gstreamer1.0-plugins-base gdk-pixbuf"
PACKAGECONFIG[packagekit] = "-Dpackagekit=true,-Dpackagekit=false,packagekit"

FILES:${PN} += " \
    ${datadir}/dbus-1 \
    ${datadir}/metainfo \
    ${datadir}/gnome-shell \
    ${datadir}/tracker3 \
"

# mandatory - not checked during configuration:
# | (org.gnome.Nautilus:863): GLib-GIO-ERROR **: 21:03:52.326: Settings schema 'org.freedesktop.Tracker.Miner.Files' is not installed
RDEPENDS:${PN} += "tracker-miners bubblewrap"
