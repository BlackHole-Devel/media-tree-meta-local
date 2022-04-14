DEPENDS = "virtual/kernel module-init-tools"
RDEPENDS:${PN} += "kmod"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

do_configure[depends] += "virtual/kernel:do_shared_workdir"

SRC_DATE = "2022-03-16"
SRC_SHA = "71e6d0608e4d"
SRC_URI[sha256sum] = "d1ea0113abcaa711d635727d32a2ca4b9dc5980313180528fd22ec8eda615cb8"

#before drop 3.10.x"
#SRCREV_mediabuild = "37374d4db55fc03d21b2dbe722be50fb164cb45c"
#before drop < 4.4"


#SRCREV_qtwebflix = "${AUTOREV}"
#SRCREV_qtdbusextended = "34971431233dc408553245001148d34a09836df1"
#SRCREV_qtmpris = "7251898353f1f5804c9480172ad7df88c4fe7eb6"
#SRCREV_FORMAT = "qtwebflix"
#
#SRC_URI = "git://github.com/gort818/qtwebflix.git;protocol=https;name=qtwebflix \
#           git://github.com/nemomobile/qtdbusextended.git;destsuffix=git/lib/qtdbusextended;branch=master;name=qtdbusextended;protocol=https \
#           git://git.merproject.org/mer-core/qtmpris.git;destsuffix=git/lib/qtmpris;branch=master;name=qtmpris;protocol=https \
#           file://0001-change-useragent.patch \
#           "

#git://git.linuxtv.org/media_tree.git;protocol=https;branch=master;name=linux;destsuffix=git/media

#vuultimo-wip
#SRCREV_mediabuild = "ef54f05ca46befdef5b9159c7e56066f12dc015a"
#SRCREV_linux = "2c9541720c66899adf6f3600984cf3ef151295ad"
#SRCREV_FORMAT = "linux"
#SRC_URI = "git://git.linuxtv.org/media_build.git;protocol=https;branch=master;name=mediabuild \
#           git://github.com/BlackHole-Devel/media_tree.git;protocol=https;branch=master;name=linux;destsuffix=git/media \
#           file://0001-make_makefile.pl-use-CROSS_COMPILE-strip.patch;patch=1 \
#           file://0002-use-system-lsdiff.patch;patch=1 \
#           file://0003-fix-media-build.patch;patch=1 \
#           file://0004-add-v4.3_regmap_write_bits.patch;patch=1 \
#           file://0005-temporary-comment-out-few-bpo.patch;patch=1 \
#           file://defconfig \
#"

#sf8008-wip
#SRCREV_mediabuild = "${AUTOREV}"
#SRCREV_linux = "71e6d0608e4d1b79069990c7dacb3600ced28a3b"
#SRCREV_FORMAT = "linux"
#SRC_URI = "git://github.com/BlackHole-Devel/media_build.git;protocol=https;branch=master-oea-new;name=mediabuild \
#           git://github.com/BlackHole-Devel/media_tree.git;protocol=https;branch=master;name=linux;destsuffix=git/media \
#           file://defconfig \
#"

SRCREV_mediabuild = "${AUTOREV}"
SRCREV_linux = "71e6d0608e4d1b79069990c7dacb3600ced28a3b"
SRCREV_FORMAT = "linux"
SRC_URI = "git://github.com/BlackHole-Devel/media_build.git;protocol=https;branch=master-oea-new;name=mediabuild \
           http://linuxtv.org/downloads/drivers/linux-media-${SRC_DATE}-${SRC_SHA}.tar.bz2;unpack=0 \
           file://defconfig \
"

#allmispel
#SRC_URI:append:vuduo = "file://vu_keep_compatibility.patch "
#SRC_URI:append:vusolo = "file://vu_keep_compatibility.patch "
#SRC_URI:append:vuuno = "file://vu_keep_compatibility.patch "
#SRC_URI:append:vuultimo = "file://vu_keep_compatibility.patch "
#SRC_URI:append:vuzero = "file://vu_keep_compatibility.patch "
#SRC_URI:append:vusolo2 = "file://vu_keep_compatibility.patch "
#SRC_URI:append:vuduo2 = "file://vu_keep_compatibility.patch "
#SRC_URI:append:vusolose = "file://vu_keep_compatibility.patch "

#3.14.x arm
#SRC_URI:append:vusolo4k = "file://vu_keep_compatibility.patch "
#SRC_URI:append:vuuno4k = "file://vu_keep_compatibility.patch "
#SRC_URI:append:vuultimo4k = "file://vu_keep_compatibility.patch "

PR = "r0"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "KDIR=${STAGING_KERNEL_DIR} SRCDIR=${STAGING_KERNEL_DIR} OUTDIR=${STAGING_KERNEL_BUILDDIR}"

do_populate_sysroot[noexec] = "1"

#do_configure:prepend:mipsel() {
#    CUR=`pwd`
#    cp ${WORKDIR}/sit2_op.o ${S}/v4l/sit2_op.o
#    make DIR=${STAGING_KERNEL_BUILDDIR} allyesconfig
#    cd $CUR
#}
#
#do_configure:prepend:arm() {
#    CUR=`pwd`
#    cp ${WORKDIR}/sit2_op.o_150322_arm ${S}/v4l/sit2_op.o
#    make DIR=${STAGING_KERNEL_BUILDDIR} allyesconfig
#    cd $CUR
#}

do_configure:prepend() {
    cp ${WORKDIR}/linux-media-${SRC_DATE}-${SRC_SHA}.tar.bz2 ${S}/linux/linux-media.tar.bz2
    md5sum ${S}/linux/linux-media.tar.bz2 > ${S}/linux/linux-media.tar.bz2.md5
    CUR=`pwd`
    rm -f v4l/.version
#    make -C linux dir DIR=../media/
    make -C linux untar
#<------>./build
#    make DIR=${STAGING_KERNEL_BUILDDIR} allyesconfig
    cd $CUR
}

require media-tree-modules.inc
