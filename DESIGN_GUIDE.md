# 🎨 Complaint System - UI/UX Design Guide

## Color Palette

### Primary Colors
```
Dark Professional Blue-Grey
RGB: 44, 62, 80
HEX: #2C3E50
Usage: Main background, panels
```

```
Modern Sky Blue
RGB: 52, 152, 219
HEX: #3498DB
Usage: Headers, accents, hover effects
```

### Secondary Colors
```
Success Green
RGB: 46, 204, 113
HEX: #2ECC71
Usage: Success buttons, confirmations

Warning Yellow
RGB: 241, 196, 15
HEX: #F1C40F
Usage: Section titles, important info

Error Red
RGB: 231, 76, 60
HEX: #E74C3C
Usage: Cancel buttons, errors

Darker Shade (Input Fields)
RGB: 52, 73, 94
HEX: #344A5E
Usage: Text input backgrounds
```

## Typography

### Font Family
**Segoe UI** - Professional, clean, modern

### Font Sizes & Weights
```
Window Title: Bold 20-24px
Section Headers: Bold 14px
Labels: Regular 14px
Body Text: Regular 13px
Input Text: Regular 13-14px
Button Text: Regular 13-16px
```

### Examples
```
Main Title: "Complaint Management System" (Bold 24px)
Section Title: "File a New Complaint" (Bold 20px)
Label: "Complaint #:" (Regular 14px)
Input: "Your complaint details..." (Regular 13px)
Button: "✓ Submit" (Regular 16px)
```

## Component Styling

### Buttons
**Default State**
- Background: #3498DB (Accent Blue)
- Text: #FFFFFF (White)
- Border: None
- Padding: 8px horizontal, 15px vertical
- Font: 13-16px Segoe UI Regular
- Cursor: Hand

**Hover State**
- Background: #2980B9 (Darker Blue)
- Text: #FFFFFF (White)
- Smooth transition

**Color Variants**
```
Submit/Save: #2ECC71 (Green) → #27AE60 (Darker Green)
Cancel: #E74C3C (Red) → #C0392B (Darker Red)
Search: #2ECC71 (Green) → #27AE60 (Darker Green)
```

### Text Input Fields
- Background: #344A5E (Darker shade)
- Text Color: #FFFFFF (White)
- Caret Color: #FFFFFF (White)
- Border: None, clean edges
- Padding: 8px
- Font: 13-14px Segoe UI
- Border on focus: Subtle glow effect

### Text Areas
- Background: #344A5E
- Text Color: #FFFFFF
- Line Wrap: Enabled
- Word Wrap: Enabled
- Font: 12-13px Segoe UI
- Padding: 8-10px

### Tables
- Background: #344A5E
- Text: #FFFFFF
- Header Background: #3498DB
- Header Text: #FFFFFF
- Selection: #3498DB highlight
- Grid Color: #647D94 (subtle)
- Row Height: 25px

### Dialogs & Windows
- Background: #2C3E50 (Primary)
- Title Bar: Standard system
- Content Padding: 20px
- Section Spacing: 10-20px

### Panels & Containers
**Header Panels**
- Background: #3498DB
- Padding: 15px top/bottom, 20px left/right
- Content: White text, bold 20px

**Content Panels**
- Background: #2C3E50
- Padding: 20-30px

**Footer Panels**
- Background: #2C3E50
- Padding: 15-20px

## Layout Structure

### Main Window (700 x 650px)
```
┌─────────────────────────────────┐
│    Header (Dark Blue Accent)    │  Height: 60px
├─────────────────────────────────┤
│                                 │
│      Menu Items (4 Buttons)     │  Height: 400px
│      with icons                 │
│                                 │
├─────────────────────────────────┤
│    Footer (Logout Button)       │  Height: 60px
└─────────────────────────────────┘
```

### Register Dialog (650 x 550px)
```
┌─────────────────────────────────┐
│ Header: File a New Complaint    │
├─────────────────────────────────┤
│ Complaint #: [Auto-filled]      │
│ Department: [Dropdown ▼]        │
│ Complaint Details: [Large Text] │
│                                 │
│ [✓ Submit]  [✕ Cancel]         │
└─────────────────────────────────┘
```

### Status Dialog (700 x 500px)
```
┌─────────────────────────────────┐
│ Header: Track Complaint Status  │
├─────────────────────────────────┤
│ Complaint #: [Input] [🔍 Search]│
├─────────────────────────────────┤
│ Status Details:                 │
│ [Large text area showing status]│
│                                 │
└─────────────────────────────────┘
```

### Admin Panel (900 x 700px)
```
┌─────────────────────────────────┐
│ Header: Manage Complaints       │
├─────────────────────────────────┤
│ ┌─────────────────────────────┐ │
│ │ Complaint #│Dept│Text│Soln │ │ (Scrollable Table)
│ └─────────────────────────────┘ │
├─────────────────────────────────┤
│ Add/Update Solution:            │
│ Complaint #: [Input]            │
│ Solution: [Large Text Area]     │
│                  [✓ Submit]     │
└─────────────────────────────────┘
```

## Spacing & Padding Guide

### Margins (External)
- Window margins: 20px
- Panel margins: 15-20px
- Component spacing: 10-15px

### Padding (Internal)
- Button padding: 8-15px
- Input field padding: 6-8px
- Text area padding: 8-10px
- Panel padding: 20-30px
- Header padding: 15px vertical, 20px horizontal

## Icons & Emojis

Used throughout the interface for quick visual recognition:

```
📝 Register a Complaint
🔍 Check Complaint Status
📋 View All Complaints
📊 View Report
🚪 Logout
✓ Success/Submit
✕ Cancel/Close
❌ Error
⏳ Pending
```

## Transitions & Effects

### Button Hover
- Smooth color transition
- Cursor changes to hand pointer
- Instant visual feedback

### Window Management
- Modal dialogs
- Centered positioning
- Smooth appearance

### Validation
- Clear error messages
- Helpful guidance
- Color-coded feedback

## Accessibility

### Contrast Ratios
- Text on background: High contrast (white on dark)
- Button text: High contrast
- All text readable and clear

### Font Sizes
- Minimum 13px for body text
- 14px+ for labels
- 20px+ for titles

### Interaction
- Clear clickable elements
- Mouse cursor feedback
- Keyboard support (Enter key in search)

## Dark Theme Benefits

✅ **Eye Comfort**: Reduced eye strain in low light
✅ **Modern Look**: Professional appearance
✅ **Energy Efficient**: Less power consumption on OLED screens
✅ **Focus**: Better content visibility
✅ **Accessibility**: Good contrast ratios

## Design Principles Applied

1. **Consistency**: Same styling across all windows
2. **Hierarchy**: Clear visual organization
3. **Clarity**: Simple, understandable interface
4. **Feedback**: Immediate user feedback
5. **Efficiency**: Quick task completion
6. **Aesthetics**: Modern, professional design

## Examples of Styling Code

### Button Creation
```java
JButton btn = new JButton("✓ Submit");
btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
btn.setBackground(new Color(46, 204, 113));
btn.setForeground(new Color(255, 255, 255));
btn.setFocusPainted(false);
btn.setBorderPainted(false);
btn.setOpaque(true);
btn.setMargin(new Insets(8, 20, 8, 20));
```

### Panel Styling
```java
JPanel panel = new JPanel();
panel.setBackground(new Color(44, 62, 80));
panel.setBorder(new EmptyBorder(20, 30, 20, 30));
```

### Input Field Styling
```java
JTextField field = new JTextField();
field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
field.setBackground(new Color(52, 73, 94));
field.setForeground(new Color(255, 255, 255));
field.setBorder(new EmptyBorder(8, 8, 8, 8));
```

---

This design guide ensures consistency and professionalism throughout the Complaint Management System.

**Design Version**: 2.0 - Modern Dark Theme
**Last Updated**: December 16, 2025
