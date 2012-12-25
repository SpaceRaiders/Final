% -----------------------------------------------------------------------
% pi1.cls
% Based on pi1-muster.tex and defs.tex used before 2003 for
% PI 1 in Bremen.
% Adapted by Thomas Röfer and Malte Kuhn (uni@monkz.de)

\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{pi1}
\LoadClass[11pt,a4paper,twoside]{article}
\usepackage[ngerman]{babel}
\usepackage[ansinew]{inputenc}
\usepackage{graphicx}
\usepackage{moreverb}
\usepackage{fancyhdr}
\usepackage{listings}

% -----------------------------------------------------------------------
% Page layout

% enlarge page
\setlength{\textwidth}{16cm}
\setlength{\topmargin}{-1cm}
\setlength{\evensidemargin}{0cm}
\setlength{\oddsidemargin}{0cm}
\setlength{\textheight}{24cm}

% skip between paragraphs
\setlength{\parskip}{1ex}
% ... and no indentation at start of a new paragraph
\setlength{\parindent}{0ex}

\newcommand{\thissemester}{WS 2012/13}
\newcommand{\thistitle}{Praktische Informatik I}

\pagestyle{fancy}
\thispagestyle{empty}

%Layout for lstlistings
\lstloadlanguages{java} % Java sprache laden, notwendig wegen option 'savemem'
\lstset{numbers=left, numberstyle=\tiny, numbersep=5pt}
\lstset{literate=%
{Ö}{{\"O}}1
{Ä}{{\"A}}1
{Ü}{{\"U}}1
{ß}{{\ss}}1
{ü}{{\"u}}1
{ä}{{\"a}}1
{ö}{{\"o}}1
}
\lstset{basicstyle=\ttfamily\scriptsize} \lstset{showspaces=false}
\lstset{showtabs=false} \lstset{showstringspaces=false}
\lstset{keywordstyle=\bfseries} \lstset{tabsize=2}
\lstset{frameround=ffff} \lstset{extendedchars=true}
\lstset{stringstyle=\ttfamily} \lstset{commentstyle=\ttfamily}
\lstset{postbreak=\space, breakindent=5pt, breaklines}


% -----------------------------------------------------------------------
% Main macros

\renewcommand{\maketitle}[7]
          {\@maketitle{#1}{#2}{#3}{#4}{#5}{#6}{#7}{�bungsblatt}}
\renewcommand\thesection{Aufgabe \arabic{section}}
\newcommand\skipsection{\stepcounter{section}}

% -----------------------------------------------------------------------
% Misc macros

\newcommand{\grenzop}[3]{\mathop{#1}\limits^{#2}_{#3}}
\newcommand{\langrechtspfeil}[2]{\grenzop{\longrightarrow}{#1}{#2}}
\newcommand{\bob}[0]{\hspace*{\fill}$\Box$}

% -----------------------------------------------------------------------

\renewcommand{\@maketitle}[8]
{
    \begin{tabular}[t]{lr}
      \begin{tabular}[t]{l}
        \begin{tabular}[t]{l@{\hskip 10mm}l}
            \thistitle & \thissemester \hspace{4ex} \\
            TutorIn: {#3}  &  Gruppe: {#4} \\
        \end{tabular} \vspace{5mm} \\
        {\Huge\sf {#8} {#1} } \\[1.5ex]
        {\Large Aufgabenl\"osung} \\[1ex]
        {\Large Abgabe: {#2}} \\[1ex]
      \end{tabular}
      &
      \begin{tabular}[t]{l}
          {#5}\\
          {#6}\\
          {#7}\\
      \end{tabular} \\[2ex]
    \end{tabular}\\
    \rule{\linewidth}{1pt}

% -----------------------------------------------------------------------
% Create headings (Thanks to Elmar Loos)

    \fancyhead{} %clear all fields
    \fancyhead[LO,RE]{PI 1, \thissemester}
    \fancyhead[RO,LE]{TutorIn: {#3}, Gruppe: {#4}, {#5} et al.}
    \fancyfoot{} %clear all fields
    \fancyfoot[RO,LE]{\thepage}
}

\endinput
