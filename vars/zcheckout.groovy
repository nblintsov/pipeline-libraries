#!/usr/bin/groovy

def call(String source = '', String target = '') {
    checkout(
        changelog: true,
        poll: true,
        scm: [
            $class: 'GitSCM',
            branches: [
                [
                    name: "origin/${source}"
                ]
            ],
            doGenerateSubmoduleConfigurations: false,
            extensions: [
                [
                    $class: 'WipeWorkspace'
                ],
                [
                    $class: 'CleanBeforeCheckout'
                ],
                [
                    $class: 'LocalBranch',
                    localBranch: 'jenkins/master'
                ],
                [
                    $class: 'PreBuildMerge',
                    options: [
                        fastForwardMode: 'FF',
                        mergeRemote: 'origin',
                        mergeTarget: '${target}'
                    ]
                ]
            ],
            submoduleCfg: [],
        ]
    )
}
