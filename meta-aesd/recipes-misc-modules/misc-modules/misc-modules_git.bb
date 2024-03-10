# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-zeog1488.git;protocol=ssh;branch=master \
           file://0001-Modified-makefile.patch \
           file://InitModules \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "e7061339e2ea29eff44b1d2044de367f41c6f0c5"

S = "${WORKDIR}/git"

inherit module

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/misc-modules"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

FILES:${PN} += "${S}/../InitModules ${sysconfdir}/init.d/InitModules"

inherit update-rc.d 

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "InitModules"

do_install:append () {
    install -d ${D}${sysconfdir}/init.d
    install	-m 0755 ${S}/../InitModules ${D}${sysconfdir}/init.d
}